import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.css']
})
export class RegisterDialogComponent {
  registerFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<RegisterDialogComponent>) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitRegisterForm(registerForm: NgForm): void {
    if (registerForm.valid) {
      // Here you can add your registration logic
      console.log('Registered successfully.');
      this.closeDialog();
    } else {
      console.log('Invalid registration form.');
    }
  }
}
