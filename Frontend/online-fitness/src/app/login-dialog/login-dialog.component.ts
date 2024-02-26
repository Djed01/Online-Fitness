import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { ActivationDialogComponent } from '../activation-dialog/activation-dialog.component';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent {
  loginFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<LoginDialogComponent>,private dialog: MatDialog,) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitLoginForm(loginForm: NgForm): void {
    if (loginForm.valid) {
      // Here you can add your login logic
      console.log('Logged in successfully.');
      this.closeDialog();
      const dialogRef = this.dialog.open(ActivationDialogComponent, {
      });
    } else {
      console.log('Invalid login form.');
    }
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
