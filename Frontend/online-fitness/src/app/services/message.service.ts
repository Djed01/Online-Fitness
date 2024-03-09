import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Message } from '../models/message.model';
import { MessageRequest } from '../models/messageRequest.model';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http:HttpClient) { }

  
  getMessagesByUserId(userId:number) {
    return this.http.get<any[]>(`http://localhost:8080/api/message/${userId}`);
  }

  sendMessage(message:MessageRequest){
    return this.http.post<Message>(`http://localhost:8080/api/message`,message);
  }
}
