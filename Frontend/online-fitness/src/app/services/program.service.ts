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

  getAllByStatusPrograms() {
    return this.http.get<Program[]>(`http://localhost:8080/api/program/status`);
  }

  getProgramById(id:number){
    return this.http.get<Program>(`http://localhost:8080/api/program/${id}`);
  }

  getProgramsByUserId(userId:number){
    return this.http.get<Program[]>(`http://localhost:8080/api/program/user/${userId}`);
  }

  createProgram(program:Program){
    return this.http.post<Program>(`http://localhost:8080/api/program`,program);
  }

  deleteProgram(id:number){
    return this.http.delete(`http://localhost:8080/api/program/${id}`);
  }
  
}
