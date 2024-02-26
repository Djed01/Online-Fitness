import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-profile-dialog',
  templateUrl: './edit-profile-dialog.component.html',
  styleUrl: './edit-profile-dialog.component.css'
})
export class EditProfileDialogComponent {
  editProfileFormValues: any = {};
  selectedFile: File | null = null;

  constructor(public dialogRef: MatDialogRef<EditProfileDialogComponent>, private http: HttpClient) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitEditProfileForm(registerForm: NgForm): void {
    if (registerForm.valid) {
      // Here you can add your registration logic
      console.log('Registered successfully.');
      this.closeDialog();
    } else {
      console.log('Invalid registration form.');
    }
  }
}
