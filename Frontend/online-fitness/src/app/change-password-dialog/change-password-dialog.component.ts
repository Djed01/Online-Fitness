import { Component } from '@angular/core';
import { MatDialogRef} from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../services/auth.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-change-password-dialog',
  templateUrl: './change-password-dialog.component.html',
  styleUrls: ['./change-password-dialog.component.css']
})
export class ChangePasswordDialogComponent {
  changePasswordFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<ChangePasswordDialogComponent>,
     private dialog: MatDialog,
     private authService:AuthService) {
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
        console.log('Changed successfully.');
        this.closeDialog();
        return response;
      });
    }
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
