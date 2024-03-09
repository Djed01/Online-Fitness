import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  getUserById(id:number) {
    return this.http.get<User>(`http://localhost:8080/api/user/${id}`);
  }

  getUserByUsername(username:string){
    return this.http.get<User>(`http://localhost:8080/api/user/username/${username}`);
  }

  updateUserInfo(userInfo:any){
    return this.http.put<User>(`http://localhost:8080/api/user`,userInfo);
  }

  getIdByUsername(username:string){
    return this.http.get<number>(`http://localhost:8080/api/user/id/${username}`);
  }
}
