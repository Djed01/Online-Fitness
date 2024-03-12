import { Component, EventEmitter, Output } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivityService } from '../services/activity.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-add-activity-dialog',
  templateUrl: './add-activity-dialog.component.html',
  styleUrl: './add-activity-dialog.component.css'
})
export class AddActivityDialogComponent {
  activityFormValues: any = {};
  @Output() activityAdded = new EventEmitter<any>();

  constructor(public dialogRef: MatDialogRef<AddActivityDialogComponent>,
    private dialog: MatDialog,
    private activityService:ActivityService,
    ) {}

    closeDialog(): void {
      this.dialogRef.close();
    }
  
    submitActivityForm(activityForm: NgForm): void {
      console.log(this.activityFormValues);
      const token = localStorage.getItem('token');
      if (token) {
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
          this.activityService.addActivity(this.activityFormValues, userId).subscribe(
            (response) => {
              console.log(response);
              // Emit the added activity
              this.activityAdded.emit(response);
              this.closeDialog();
            }
          )
        }
      }
    }
}
