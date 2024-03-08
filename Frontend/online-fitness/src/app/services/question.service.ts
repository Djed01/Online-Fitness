import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Question } from '../models/question.model';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) { }


  createQuestion(question:Question){
    return this.http.post<Question>(`http://localhost:8080/api/question`,question);
  }
}
