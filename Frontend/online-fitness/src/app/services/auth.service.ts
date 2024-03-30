import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/auth'; 

  constructor(private http: HttpClient) { }

  login(request: any): Observable<any>{
    return this.http.post(`${this.baseUrl}/login`,request);
  }

  regenerateLink(request:any):Observable<any>{
    return this.http.post(`${this.baseUrl}/regenerate`,request);
  }

  signup(request: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, request);
  }

  activate(token:any):Observable<any>{
    return this.http.get(`${this.baseUrl}/activate?token=${token}`);
  }

  changePassword(request:any):Observable<any>{
    return this.http.post(`${this.baseUrl}/changepassword`,request);
  }
}
