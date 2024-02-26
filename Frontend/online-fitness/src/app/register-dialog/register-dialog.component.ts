import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.css']
})
export class RegisterDialogComponent {
  registerFormValues: any = {};
  selectedFile: File | null = null;

  constructor(public dialogRef: MatDialogRef<RegisterDialogComponent>, private http: HttpClient) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitRegisterForm(registerForm: NgForm): void {
    if (registerForm.valid) {
      // Here you can add your registration logic
      console.log('Registered successfully.');
      this.uploadAvatar();
      this.closeDialog();
    } else {
      console.log('Invalid registration form.');
    }
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
  }

   uploadAvatar(): void {
    if (!this.selectedFile) return;

    const formData = new FormData();
    formData.append('avatar', this.selectedFile, `${this.registerFormValues.username}.png`);

    // Assuming 'assets/avatars' is within the public directory
    const url = '/assets/avatars/' + `${this.registerFormValues.username}.png`;
    this.http.put(url, formData).subscribe(
      (response) => {
        console.log('Avatar saved successfully.');
      },
      (error) => {
        console.error('Error saving avatar:', error);
      }
    );
  }
}
