import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-payment-dialog',
  templateUrl: './payment-dialog.component.html',
  styleUrls: ['./payment-dialog.component.css']
})
export class PaymentDialogComponent {
  selectedPaymentMethod: string = '';
  creditCardFormValues: any = {};
  paypalFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<PaymentDialogComponent>) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitCreditCardForm(creditCardForm: NgForm): void {
    if (creditCardForm.valid) {
      console.log('Credit card payment processed successfully.');
      this.closeDialog();
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
