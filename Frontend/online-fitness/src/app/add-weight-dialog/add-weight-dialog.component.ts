import { Component, EventEmitter, Output  } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { jwtDecode } from 'jwt-decode';
import { ActivityService } from '../services/activity.service';

@Component({
  selector: 'app-add-weight-dialog',
  templateUrl: './add-weight-dialog.component.html',
  styleUrl: './add-weight-dialog.component.css'
})
export class AddWeightDialogComponent {
  weightFormValues: any = {};
  @Output() weightAdded = new EventEmitter<any>();

  constructor(public dialogRef: MatDialogRef<AddWeightDialogComponent>,
    private dialog: MatDialog,
    private activityService:ActivityService
    ) {}

    closeDialog(): void {
      this.dialogRef.close();
    }
  
    submitWeightForm(weightForm: NgForm): void {
      const token = localStorage.getItem('token');
      if (token) {
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
          this.activityService.addBodyWeightRecord(this.weightFormValues, userId).subscribe(
            (response) => {
              console.log(response);
              // Emit the added activity
              this.weightAdded.emit(response);
              this.closeDialog();
            });
        }
      }
    }
}
