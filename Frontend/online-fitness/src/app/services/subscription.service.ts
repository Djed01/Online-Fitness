import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subscription } from '../models/subscription.model';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  constructor(private http:HttpClient) {

  }

  getSubscriptions(userId:number) {
    return this.http.get<Subscription[]>(`http://localhost:8080/api/subscription/${userId}`);
  }

  subscribeOrUnsubscribe(categoryId: number, userId: number) {
    return this.http.post(`http://localhost:8080/api/subscription/${categoryId}/${userId}`,null);
  }
  
}