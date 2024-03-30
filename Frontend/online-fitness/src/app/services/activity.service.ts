import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable,of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Activity } from '../models/activity.model';


@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) { }

  getActivities(userId: number) {
    return this.http.get<Activity[]>(`http://localhost:8080/api/activity/${userId}`);
  }

  addActivity(activity: Activity, userId:number): Observable<any> {
    return this.http.post(`http://localhost:8080/api/activity/${userId}`, activity);
  }

  deleteActivity(id: number) {
    return this.http.delete(`http://localhost:8080/api/activity/${id}`);
  }

  getBodyWeightData(id:number):Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/api/body-weight/${id}`);
  }

  addBodyWeightRecord(weight: number, id:number): Observable<any> {
    return this.http.post(`http://localhost:8080/api/body-weight/${id}`,weight);
  }

  downloadPdf(userId:number){
    return this.http.get(`http://localhost:8080/api/activity/pdf/${userId}`);
  }
}
