import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-change-password-dialog',
  templateUrl: './change-password-dialog.component.html',
  styleUrls: ['./change-password-dialog.component.css']
})
export class ChangePasswordDialogComponent {
  changePasswordFormValues: any = {};


  constructor(public dialogRef: MatDialogRef<ChangePasswordDialogComponent>, private dialog: MatDialog) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitChangePasswordForm(changePasswordForm: NgForm): void {
    if (changePasswordForm.valid) {
      // Here you can add your login logic
      console.log('Changed successfully.');
      this.closeDialog();
    } else {
      console.log('Invalid change.');
    }
  }

  passwordsMatch(): boolean {
    return this.changePasswordFormValues.newPassword === this.changePasswordFormValues.reenteredPassword;
  }

  passwordsDoNotMatch(): boolean {
    return this.changePasswordFormValues.newPassword !== this.changePasswordFormValues.reenteredPassword;
  }
}
