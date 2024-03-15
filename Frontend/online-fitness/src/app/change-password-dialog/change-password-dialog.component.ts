import { Component } from '@angular/core';
import { MatDialogRef} from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../services/auth.service';
import { jwtDecode } from 'jwt-decode';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-change-password-dialog',
  templateUrl: './change-password-dialog.component.html',
  styleUrls: ['./change-password-dialog.component.css']
})
export class ChangePasswordDialogComponent {
  changePasswordFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<ChangePasswordDialogComponent>,
     private dialog: MatDialog,
     private authService:AuthService,
     private snackBar: MatSnackBar,) {
     }

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitChangePasswordForm(changePasswordForm: NgForm): void {
    if (changePasswordForm.valid) {
      const token = localStorage.getItem('token');
      if(token){
        const decodedToken: any = jwtDecode(token);
        const username = decodedToken.sub;
        this.changePasswordFormValues.username = username;
      console.log(this.changePasswordFormValues);
      this.authService.changePassword(this.changePasswordFormValues).subscribe((response)=>{
        //console.log('Changed successfully.');
        this.snackBar.open("Changed successfully.", 'Close', {
          duration: 3000,
        });
        this.closeDialog();
        return response;
      });
    }
    } else {
      this.snackBar.open("Invalid change.", 'Close', {
        duration: 3000,
      });
      //console.log('Invalid change.');
    }
  }

  passwordsMatch(): boolean {
    return this.changePasswordFormValues.newPassword === this.changePasswordFormValues.reenteredPassword;
  }

  passwordsDoNotMatch(): boolean {
    return this.changePasswordFormValues.newPassword !== this.changePasswordFormValues.reenteredPassword;
  }
}
