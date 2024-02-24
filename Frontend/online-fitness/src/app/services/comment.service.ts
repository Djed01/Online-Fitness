import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../models/comment.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http:HttpClient) { }

  getAllCommentsByProgramId(id:number) {
    return this.http.get<Comment[]>(`http://localhost:8080/api/comment/program/${id}`);
  }
}
