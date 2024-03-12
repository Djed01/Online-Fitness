import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-add-activity-dialog',
  templateUrl: './add-activity-dialog.component.html',
  styleUrl: './add-activity-dialog.component.css'
})
export class AddActivityDialogComponent {
  activityFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<AddActivityDialogComponent>,
    private dialog: MatDialog,
    ) {}

    closeDialog(): void {
      this.dialogRef.close();
    }
  
    submitActivityForm(activityForm: NgForm): void {
    }
}
