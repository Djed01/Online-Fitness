import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor() { }

  getActivities(): Observable<any[]> {
    // Simulated mock data
    const activities = [
      { id: 1, userName: 'John Doe', sets: 3, reps: 12, weight: 50, date: new Date('2024-03-10') },
      { id: 2, userName: 'Alice Smith', sets: 4, reps: 10, weight: 65, date: new Date('2024-03-09') },
      { id: 3, userName: 'Bob Johnson', sets: 2, reps: 8, weight: 80, date: new Date('2024-03-08') },
      { id: 4, userName: 'John Doe', sets: 3, reps: 12, weight: 50, date: new Date('2024-03-10') },
      { id: 5, userName: 'Alice Smith', sets: 4, reps: 10, weight: 65, date: new Date('2024-03-09') },
      { id: 6, userName: 'Bob Johnson', sets: 2, reps: 8, weight: 80, date: new Date('2024-03-08') }
    ];
    return of(activities);
  }

  deleteActivity(activityId: number): Observable<void> {
    // Simulate delete action
    return of();
  }


  getBodyWeightData(): Observable<any[]> {
    // Mock data with body weight and date
    const mockData = [
      { weight: 70, date: '2024-03-10' },
      { weight: 71, date: '2024-03-11' },
      { weight: 69, date: '2024-03-12' }
    ];
    return of(mockData);
  }

  // Mock function to add a new record of body weight
  addBodyWeightRecord(weight: number, date: Date): Observable<any> {
    // Mock adding record functionality
    return of({ weight, date });
  }
}
