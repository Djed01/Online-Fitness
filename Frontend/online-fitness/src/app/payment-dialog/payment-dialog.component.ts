import { Component,Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { Participation } from '../models/participation.model';
import { ParticipationService } from '../services/participation.service';
import { jwtDecode } from 'jwt-decode';
import { MatSnackBar } from '@angular/material/snack-bar';

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
    private snackBar: MatSnackBar,
  ) {
    this.programId = data.programId;
    console.log("ID;:"+this.programId);
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  participate(){
      const token = localStorage.getItem('token');
      if(token){
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
          this.participation.date = new Date();
          this.participation.programId = this.programId;
          this.participation.userId = userId;
          this.participationService.addParticipation(this.participation).subscribe((response)=>{
            this.closeDialog();
          });
        }
      }
  }

  submitCreditCardForm(creditCardForm: NgForm): void {
    if (creditCardForm.valid) {
      this.participate();
    } else {
      this.snackBar.open("Invalid credit card payment form.", 'Close', {
        duration: 3000,
      });
      console.log('Invalid credit card payment form.');
    }
  }

  submitPayPalForm(paypalForm: NgForm): void {
    if (paypalForm.valid) {
      this.participate();
      this.snackBar.open("PayPal payment processed successfully.", 'Close', {
        duration: 3000,
      });
      console.log('PayPal payment processed successfully.');
    } else {
      this.snackBar.open("Invalid PayPal payment form.", 'Close', {
        duration: 3000,
      });
      console.log('Invalid PayPal payment form.');
    }
  }

  submitInPerson():void{
    this.participate();
    this.snackBar.open("Confirmed successfully.", 'Close', {
      duration: 3000,
    });
  }


  submitForm(): void {
    if (this.selectedPaymentMethod === 'credit_card') {
      this.submitCreditCardForm(this.creditCardFormValues);
    } else if (this.selectedPaymentMethod === 'paypal') {
      this.submitPayPalForm(this.paypalFormValues);
    } 
  }
}
