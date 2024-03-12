import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-add-weight-dialog',
  templateUrl: './add-weight-dialog.component.html',
  styleUrl: './add-weight-dialog.component.css'
})
export class AddWeightDialogComponent {
  weightFormValues: any = {};

  constructor(public dialogRef: MatDialogRef<AddWeightDialogComponent>,
    private dialog: MatDialog,
    ) {}

    closeDialog(): void {
      this.dialogRef.close();
    }
  
    submitWeightForm(weightForm: NgForm): void {
    }
}
