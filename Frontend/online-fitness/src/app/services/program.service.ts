import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Program } from '../models/program.model';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {

  constructor(private http: HttpClient) { }

  getAllPrograms() {
    return this.http.get<Program[]>(`http://localhost:8080/api/program`);
  }

  getProgramById(id:number){
    return this.http.get<Program>(`http://localhost:8080/api/program/${id}`);
  }
  
}
