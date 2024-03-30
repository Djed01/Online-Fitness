import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AvatarService } from '../services/avatar.service';
import { AuthService } from '../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.css']
})
export class RegisterDialogComponent {

  profileForm = {
    username: '',
    password: '',
    repeatPassword: '',
    email: '',
    name: '',
    surname: '',
    city: '',
    image: null
  };
  loading: boolean = false;
  selectedFile: File | null = null;

  constructor(
    public dialogRef: MatDialogRef<RegisterDialogComponent>,
    private avatarService: AvatarService,
    private authService: AuthService,
    private snackBar: MatSnackBar
  ) {}

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitRegisterForm(): void {
    this.loading = true;
    this.authService.signup(this.profileForm).subscribe(
      (response) => {
        this.snackBar.open("Successfully registered.", 'Close', {
          duration: 3000,
        });
        console.log('User registered successfully:', response);
        this.uploadAvatar(response.id);
        this.closeDialog();
      },
      (error) => {
        console.error('Error registering user:', error);
      }
    ).add(() => this.loading = false);
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
  }

  uploadAvatar(userId:number): void {
    if (!this.selectedFile) return;
    this.avatarService.uploadAvatar(this.selectedFile, userId).subscribe((data: any) => {
      console.log(data);
    });
  }
}
