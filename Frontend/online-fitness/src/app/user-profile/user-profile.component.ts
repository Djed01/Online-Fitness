import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditProfileDialogComponent } from '../edit-profile-dialog/edit-profile-dialog.component';
import { ChangePasswordDialogComponent } from '../change-password-dialog/change-password-dialog.component';
import { OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { AvatarService } from '../services/avatar.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  selectedFile: File | null = null;
  userId!:number;
  user: any = {
    avatar: 'https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg',
  };

  constructor(private dialog: MatDialog,
    private userService:UserService,
    private avatarService:AvatarService) { }

  ngOnInit(){
    const token = localStorage.getItem('token');
    if(token){
      const decodedToken: any = jwtDecode(token);
      const userId = decodedToken.id;
      if (userId) {
        this.userId = userId;
        this.fetchData(userId);
      }
    }
  }

  fetchData(userId:number){
  this.userService.getUserById(userId).subscribe((response)=>{
    this.user.name = response.name;
    this.user.username = response.username;
    this.user.surname = response.surname;
    this.user.city = response.city;
    this.user.email = response.email;
    if(response.avatarId)
    this.avatarService.downloadAvatar(response.avatarId).subscribe((response)=>{
      this.user.avatar = response;
    })
  })
}

  openEditProfileDialog(user: any): void {
    const dialogRef = this.dialog.open(EditProfileDialogComponent, {
      data: { user: user }
    });
    
    dialogRef.afterClosed().subscribe(result => {
      this.fetchData(this.userId);
      console.log('The dialog was closed');
    }); 
  }

  openChangePasswordDialog():void{
    const dialogRef = this.dialog.open(ChangePasswordDialogComponent, {
      data:{username:String}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    }); 
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
    this.updateAvatar(this.userId);
  }

   updateAvatar(userId:number): void {
    if (!this.selectedFile) return;
    this.avatarService.updateAvatar(this.selectedFile,userId).subscribe((data: any) => {
      console.log(data);
      this.fetchData(this.userId);
    });
  }
}
