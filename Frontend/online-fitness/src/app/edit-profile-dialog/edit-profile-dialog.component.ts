import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-edit-profile-dialog',
  templateUrl: './edit-profile-dialog.component.html',
  styleUrl: './edit-profile-dialog.component.css'
})
export class EditProfileDialogComponent {
  editProfileFormValues: any = {};
  selectedFile: File | null = null;

  constructor(public dialogRef: MatDialogRef<EditProfileDialogComponent>, 
    private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data: any,
    private userService:UserService) {
      this.editProfileFormValues = { ...data.user };
    }

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitEditProfileForm(registerForm: NgForm): void {
    if (registerForm.valid) {
      this.userService.updateUserInfo(this.editProfileFormValues).subscribe((response)=>{
        console.log('Registered successfully.');
        this.closeDialog();
      });
    } else {
      console.log('Invalid registration form.');
    }
  }
}
