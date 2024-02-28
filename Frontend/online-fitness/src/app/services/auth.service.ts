import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/auth'; // Change this to your backend API base URL

  constructor(private http: HttpClient) { }

  signup(request: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, request);
  }
}
