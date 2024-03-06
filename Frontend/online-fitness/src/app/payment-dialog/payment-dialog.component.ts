import { Component,Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { Participation } from '../models/participation.model';
import { ParticipationService } from '../services/participation.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-payment-dialog',
  templateUrl: './payment-dialog.component.html',
  styleUrls: ['./payment-dialog.component.css']
})
export class PaymentDialogComponent {
  selectedPaymentMethod: string = '';
  creditCardFormValues: any = {};
  paypalFormValues: any = {};
  participation:Participation = {} as Participation;
  programId:number;

  constructor(
    public dialogRef: MatDialogRef<PaymentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private participationService:ParticipationService,
  ) {
    this.programId = data.programId;
    console.log("ID;:"+this.programId);
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitCreditCardForm(creditCardForm: NgForm): void {
    if (creditCardForm.valid) {
      const token = localStorage.getItem('token');
      if(token){
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
          this.participation.date = new Date();
          this.participation.programId = this.programId;
          this.participation.userId = userId;
          this.participationService.addParticipation(this.participation).subscribe((response)=>{
            console.log('Credit card payment processed successfully.');
          this.closeDialog();
          });
        }
      }
    } else {
      console.log('Invalid credit card payment form.');
    }
  }

  submitPayPalForm(paypalForm: NgForm): void {
    if (paypalForm.valid) {
      console.log('PayPal payment processed successfully.');
      this.closeDialog();
    } else {
      console.log('Invalid PayPal payment form.');
    }
  }


  submitForm(): void {
    if (this.selectedPaymentMethod === 'credit_card') {
      this.submitCreditCardForm(this.creditCardFormValues);
    } else if (this.selectedPaymentMethod === 'paypal') {
      this.submitPayPalForm(this.paypalFormValues);
    } else if(this.selectedPaymentMethod == 'in_person'){
      this.closeDialog();
    }
  }
}
