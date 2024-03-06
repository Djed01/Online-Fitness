import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Participation } from '../models/participation.model';

@Injectable({
  providedIn: 'root'
})
export class ParticipationService {
  private baseUrl = 'http://localhost:8080/api/participation';
  constructor(private http:HttpClient) { }

  addParticipation(participation:Participation): Observable<any>{
    return this.http.post(`${this.baseUrl}`,participation);
  }
}
