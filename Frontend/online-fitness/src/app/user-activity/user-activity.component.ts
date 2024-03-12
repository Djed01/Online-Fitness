import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivityService } from '../services/activity.service';
import { AddActivityDialogComponent } from '../add-activity-dialog/add-activity-dialog.component';
import { AddWeightDialogComponent } from '../add-weight-dialog/add-weight-dialog.component';

@Component({
  selector: 'app-user-activity',
  templateUrl: './user-activity.component.html',
  styleUrl: './user-activity.component.css'
})
export class UserActivityComponent {
  //Chart properties
  colorScheme = 'nightLights';
  gradient = false;
  showXAxis = true;
  showYAxis = true;
  showLegend = true;
  showXAxisLabel = true;
  showYAxisLabel = true;
  xAxisLabel = 'Time';
  yAxisLabel = 'Weight';
  autoScale = true;
  // -----------------------------


  activities: any[] = [];
  weightData: any[] = [];

  constructor(private activityService: ActivityService,
    private dialog: MatDialog,) {}

  ngOnInit() {
    this.activityService.getActivities().subscribe(activities => {
      this.activities = activities;
    });
    this.fetchWeightData();
    console.log(this.weightData);
  }

  deleteActivity(activityId: number) {
    this.activities = this.activities.filter(activity => activity.id !== activityId);
  }

  addNewActivity(){
    
  }

  fetchWeightData() {
    this.activityService.getBodyWeightData().subscribe(data => {
      // Transforming data to the format expected by apx-chart
      this.weightData = data.map(item => {
        return {
          name: item.date, // Using date as the name
          value: item.weight // Using weight as the value
        };
      });
    });
  }

  addNewRecord() {
    // Mock new record data
    const newWeight = 72;
    const newDate = new Date(); // Use current date
    this.activityService.addBodyWeightRecord(newWeight, newDate).subscribe(() => {
      // Assuming success, fetch updated data
      this.fetchWeightData();
    });
  }

  openAddActivityDialog(){
    const dialogRef = this.dialog.open(AddActivityDialogComponent, {
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    }); 
  }

  openAddWeightDialog(){
    const dialogRef = this.dialog.open(AddWeightDialogComponent, {
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    }); 
  }
}
