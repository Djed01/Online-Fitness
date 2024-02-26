import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-activation-dialog',
  templateUrl: './activation-dialog.component.html',
  styleUrl: './activation-dialog.component.css'
})
export class ActivationDialogComponent {
  activationFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<ActivationDialogComponent>,private dialog: MatDialog,) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitActivationForm(activationForm: NgForm): void {
    if (activationForm.valid) {
      // Here you can add your login logic
      console.log('Logged in successfully.');
      this.closeDialog();
    } else {
      console.log('Invalid login form.');
    }
  }
}
