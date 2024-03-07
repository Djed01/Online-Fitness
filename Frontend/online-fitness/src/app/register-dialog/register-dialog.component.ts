import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, NgForm, FormControl, Validators, } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AvatarService } from '../services/avatar.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.css']
})
export class RegisterDialogComponent {

  username = new FormControl('', [
    Validators.required,
    Validators.maxLength(50),
  ]);
  password = new FormControl('', [
    Validators.required,
    Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}'),
  ]);
  repeatPassword = new FormControl('', [
    Validators.required,
    Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}'),
    // Validators.minLength(8),
  ]);
  email = new FormControl('', [
    Validators.required,
    Validators.maxLength(255),
    Validators.email,
  ]);
  name = new FormControl('', [Validators.required, Validators.maxLength(255)]);
  surname = new FormControl('', [
    Validators.required,
    Validators.maxLength(255),
  ]);
  city = new FormControl('', [Validators.required, Validators.maxLength(255)]);
  image = new FormControl(null);
  usernameValid = true;
  emailValid = true;
  selectedFile: File | null = null;

  constructor(
    public dialogRef: MatDialogRef<RegisterDialogComponent>,
    private http: HttpClient,
    private avatarService:AvatarService,
    private authService:AuthService,
    private formBuilder:FormBuilder,
    ) {}

    profileForm = this.formBuilder.group(
      {
        username: this.username,
        password: this.password,
        repeatPassword: this.repeatPassword,
        email: this.email,
        name: this.name,
        surname: this.surname,
        city: this.city,
        image: this.image,
      }
    );
  


  closeDialog(): void {
    this.dialogRef.close();
  }

  submitRegisterForm(): void {

      this.authService.signup({
        username: this.username.value,
        password: this.password.value,
        reenterPassword: this.repeatPassword.value,
        email: this.email.value,
        name: this.name.value,
        surname: this.surname.value,
        city: this.city.value,
      }).subscribe(
        (response) => {
          console.log('User registered successfully:', response);
          this.uploadAvatar(response.id);
          // Optionally, you can close the dialog here
          this.closeDialog();
        },
        (error) => {
          console.error('Error registering user:', error);
        }
      );
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
  }

   uploadAvatar(userId:number): void {
    if (!this.selectedFile) return;
    this.avatarService.uploadAvatar(this.selectedFile,userId).subscribe((data: any) => {
      console.log(data);
    });
  }


}
