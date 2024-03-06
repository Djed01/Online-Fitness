import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../models/comment.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private baseUrl = 'http://localhost:8080/api/comment';
  constructor(private http:HttpClient) { }

  getAllCommentsByProgramId(id:number) {
    return this.http.get<Comment[]>(`${this.baseUrl}/program/${id}`);
  }

  addComment(comment:Comment): Observable<any>{
    return this.http.post(`${this.baseUrl}`,comment);
  }
}
