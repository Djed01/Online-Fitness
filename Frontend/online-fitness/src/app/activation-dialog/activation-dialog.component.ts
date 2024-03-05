import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-activation-dialog',
  templateUrl: './activation-dialog.component.html',
  styleUrl: './activation-dialog.component.css'
})
export class ActivationDialogComponent {
  activationFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<ActivationDialogComponent>,
    private dialog: MatDialog,
    private authService:AuthService) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitActivationForm(activationForm: NgForm): void {
    if (activationForm.valid) {
     this.authService.regenerateLink(this.activationFormValues).subscribe(
      (response)=>{
        console.log('Link sent successfully.');
      this.closeDialog();
      }
     );
    } else {
      console.log('Invalid form.');
    }
  }
}
