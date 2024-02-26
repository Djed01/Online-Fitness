import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Exercise } from '../models/exercise.model';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {
  API_KEY = 'Mmpbolvmgmxr6/nVnZpPIg==9WhBJttWHJGmCeLJ';
  API_URL = 'https://api.api-ninjas.com/v1/exercises';
  constructor(private http: HttpClient) {}

  public getExercises() {
    return this.http.get(this.API_URL, { headers: { 'X-Api-Key': this.API_KEY } });
  }
}
