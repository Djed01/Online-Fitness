import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { jwtDecode } from 'jwt-decode';
import { ActivityService } from '../services/activity.service';
import { AddActivityDialogComponent } from '../add-activity-dialog/add-activity-dialog.component';
import { AddWeightDialogComponent } from '../add-weight-dialog/add-weight-dialog.component';
import { Activity } from '../models/activity.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-user-activity',
  templateUrl: './user-activity.component.html',
  styleUrls: ['./user-activity.component.css'] 
})
export class UserActivityComponent implements OnInit {
  // Chart properties
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

  activities: Activity[] = [];
  weightData: any[] = [];
  pdfUrl: any = {};
  userId: number | null;

  constructor(private activityService: ActivityService,
    private dialog: MatDialog,
    private http:HttpClient) {
      this.userId = null;
    }

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken: any = jwtDecode(token);
      this.userId = decodedToken.id;// Move this here
      if (this.userId) {
        this.activityService.getActivities(this.userId).subscribe(activities => {
          console.log("ACTIVITIES:"+activities);
          this.activities = activities;
          console.log(this.weightData);
        });
        this.fetchWeightData(this.userId);
        //this.pdfUrl == this.activityService.downloadPdf(userId);
      }
    }
  }

  deleteActivity(activityId: number) {
    // console.log("ID="+activityId);
    this.activityService.deleteActivity(activityId).subscribe(
      (response)=>{
        this.activities = this.activities.filter(activity => activity.id !== activityId);
      }
    )
  }


  fetchWeightData(userId:number) {
    this.activityService.getBodyWeightData(userId).subscribe(data => {
      // Transforming data to the format expected by apx-chart
      this.weightData = data.map(item => {
        return {
          name: item.date, // Using date as the name
          value: item.weight // Using weight as the value
        };
      });
    });
  }


  openAddActivityDialog() {
    const dialogRef = this.dialog.open(AddActivityDialogComponent, {});
    dialogRef.componentInstance.activityAdded.subscribe((addedActivity) => {
      console.log('Added activity:', addedActivity);
      this.activities.push(addedActivity);
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openAddWeightDialog() {
    const dialogRef = this.dialog.open(AddWeightDialogComponent, {});
    dialogRef.componentInstance.weightAdded.subscribe((addedWeight) => {
      this.weightData.push(addedWeight);
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  downloadPdf(event: Event) {
    event.preventDefault(); // Prevent default behavior of anchor element
    const userId = this.userId; // Replace with actual user ID
    const url = `http://localhost:8080/api/activity/pdf/${userId}`;
    this.http.get(url, { responseType: 'blob' }).subscribe(response => {
      const blob = new Blob([response], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      const downloadLink = document.createElement('a');
      downloadLink.href = url;
      downloadLink.download = 'activity_report.pdf'; // You can change the filename here
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
      window.URL.revokeObjectURL(url);
    });
  }
}
