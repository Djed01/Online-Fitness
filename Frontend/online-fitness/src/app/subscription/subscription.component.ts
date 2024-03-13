import { Component, OnInit } from '@angular/core';
import { Subscription } from '../models/subscription.model';
import { SubscriptionService } from '../services/subscription.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrl: './subscription.component.css'
})
export class SubscriptionComponent implements OnInit {
  subscriptions: Subscription[] = [];
  userId: number | null;
  constructor(private subscriptionService: SubscriptionService) {
    this.userId = null;
  }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken: any = jwtDecode(token);
      this.userId = decodedToken.id;
      if (this.userId) {
        this.subscriptionService.getSubscriptions(this.userId).subscribe(
          (response) => {
            this.subscriptions = response;
          }
        );
      }
    }
  }

  toggleSubscription(subscription: Subscription): void {
    if (this.userId)
    this.subscriptionService.subscribeOrUnsubscribe(subscription.id,this.userId).subscribe(
      (response)=>{
        subscription.subscribed = !subscription.subscribed;
      }
    )
  }
}
