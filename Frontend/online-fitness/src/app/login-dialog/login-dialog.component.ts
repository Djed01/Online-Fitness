import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { ActivationDialogComponent } from '../activation-dialog/activation-dialog.component';
import { AuthService } from '../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent {
  loginFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<LoginDialogComponent>,
    private dialog: MatDialog,
    private authService:AuthService,
    private snackBar: MatSnackBar,) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitLoginForm(loginForm: NgForm): void {
    this.authService.login(this.loginFormValues).subscribe(
      (response) => {
        localStorage.setItem('token', response.token);
        console.log('Logged in successfully.');
        this.closeDialog();
      },
      (error) => {
        if (error.status === 401) {
          // InvalidUsernameException
          this.snackBar.open("Invalid username or password!", 'Close', {
            duration: 3000,
          });
          //console.log('Invalid username or password!');
        } else if (error.status === 403) {
          // NotActivatedException
          this.closeDialog();
          const dialogRef = this.dialog.open(ActivationDialogComponent, {});
          this.snackBar.open("Account not activated!", 'Close', {
            duration: 3000,
          });
          //console.log('Account not activated!');
        } else {
          // Other errors
          this.snackBar.open("An error occurred!", 'Close', {
            duration: 3000,
          });
          //console.error('An error occurred:', error);
        }
      }
    );
  }

  openRegisterDialog(){
    this.closeDialog();
    const dialogRef = this.dialog.open(RegisterDialogComponent, {
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    }); 
  }
}
