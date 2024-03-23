import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-fitness';
  isLoggedIn: boolean = false;

  constructor(private dialog: MatDialog, private router: Router) {
    this.isLoggedIn = this.isUserLoggedIn();
  }

  isUserLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }

  getLink(): string[] {
    return ['/'];
  }

  openLoginDialog(): void {
    const dialogRef = this.dialog.open(LoginDialogComponent, {});

    dialogRef.afterClosed().subscribe(result => {
      this.isLoggedIn = this.isUserLoggedIn();
      console.log('The dialog was closed');
    });
  }

  logout(): void {
    localStorage.removeItem('token'); // Remove token from local storage
    this.isLoggedIn = false; // Update login status
    this.router.navigateByUrl('/');
  }
}
