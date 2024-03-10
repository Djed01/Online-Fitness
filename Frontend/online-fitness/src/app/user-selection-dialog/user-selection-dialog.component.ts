// user-selection-dialog.component.ts
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

  constructor(
    private userService: UserService,
    public dialogRef: MatDialogRef<UserSelectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe(users => {
      this.users = users;
      this.filteredUsers = users;
    });
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
