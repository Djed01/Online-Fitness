import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AvatarService } from '../services/avatar.service';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.css']
})
export class RegisterDialogComponent {
  registerFormValues: any = {};
  selectedFile: File | null = null;

  constructor(
    public dialogRef: MatDialogRef<RegisterDialogComponent>,
    private http: HttpClient,
    private avatarService:AvatarService,
    ) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitRegisterForm(registerForm: NgForm): void {
    if (registerForm.valid) {
      // Here you can add your registration logic
      console.log('Registered successfully.');
      this.uploadAvatar();
     // this.closeDialog();
    } else {
      console.log('Invalid registration form.');
    }
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
  }

   uploadAvatar(): void {
    if (!this.selectedFile) return;
    this.avatarService.uploadAvatar(this.selectedFile).subscribe((data: any) => {
      console.log(data);
    });
  }


}
