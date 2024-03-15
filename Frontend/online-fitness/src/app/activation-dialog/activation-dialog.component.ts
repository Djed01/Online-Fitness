import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-activation-dialog',
  templateUrl: './activation-dialog.component.html',
  styleUrl: './activation-dialog.component.css'
})
export class ActivationDialogComponent {
  activationFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<ActivationDialogComponent>,
    private dialog: MatDialog,
    private authService:AuthService,
    private snackBar: MatSnackBar,) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitActivationForm(activationForm: NgForm): void {
    if (activationForm.valid) {
     this.authService.regenerateLink(this.activationFormValues).subscribe(
      (response)=>{
        this.snackBar.open("Activation link sent to your email.", 'Close', {
          duration: 3000,
        });
        // console.log('Link sent successfully.');
      this.closeDialog();
      }
     );
    } else {
      this.snackBar.open("Invalid form.", 'Close', {
        duration: 3000,
      });
      //console.log('Invalid form.');
    }
  }
}
