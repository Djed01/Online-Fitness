import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-selection-dialog',
  templateUrl: './user-selection-dialog.component.html',
  styleUrls: ['./user-selection-dialog.component.css']
})
export class UserSelectionDialogComponent {
  users: any[] = [];
  filteredUsers: any[] = [];
  selectedUser: any;
  currentUsername: string;

  constructor(
    private userService: UserService,
    public dialogRef: MatDialogRef<UserSelectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.users = data.users;
    this.currentUsername = data.currentUsername;
  }

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe(users => {
      // Filter out users that are already in the inbox
      this.users = users.filter(user => !this.isUserInInbox(user));
      // Filter out the current user
      this.users = this.users.filter(user => user.username !== this.currentUsername);
      this.filteredUsers = this.users;
    });
  }

  isUserInInbox(user: any): boolean {
    return this.data.users.some((existingUser: any) => existingUser.username === user.username);
  }

  filterUsers(event: Event) {
    const keyword = (event.target as HTMLInputElement).value;
    this.filteredUsers = this.users.filter(user =>
      user.name.toLowerCase().includes(keyword.toLowerCase())
    );
  }

  selectUser(user: any) {
    this.selectedUser = user;
  }

  submit() {
    this.dialogRef.close(this.selectedUser);
  }

  cancel() {
    this.dialogRef.close();
  }
}
