import { Component, ViewChild, ElementRef } from '@angular/core';
import { Message } from '../models/message.model';
import { UserService } from '../services/user.service';
import { MessageService } from '../services/message.service';
import { jwtDecode } from 'jwt-decode';
import { AvatarService } from '../services/avatar.service';
import { UserDetails } from '../models/userDetails.model';
import { MessageRequest } from '../models/messageRequest.model';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  @ViewChild('messageContainer') messageContainer!: ElementRef;

  avatarUrl:string = "";
  users: UserDetails[] = [];
  activeUser: UserDetails | null = null;
  messages: Message[] = [];
  newMessage: string = '';
  image:string = "https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg";

  constructor(private messageService: MessageService, 
    private userService: UserService,
    private avatarService: AvatarService) {
    this.getMessages();
  }

  selectUser(user: UserDetails) {
    this.activeUser = user;
    this.messages = user.messages || [];
  }

  sendMessage() {
    if (this.newMessage.trim() !== '' && this.activeUser) {
      const messageRequest:MessageRequest = {} as MessageRequest;

      messageRequest.content = this.newMessage;

      const token = localStorage.getItem('token');
      if (token) {
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if(userId){
          messageRequest.senderId = userId;
        }
      }

      console.log(this.activeUser);
      this.userService.getIdByUsername(this.activeUser.username).subscribe((response)=>{
        messageRequest.receiverId = response;

        this.messageService.sendMessage(messageRequest).subscribe((response)=>{
          const newMessage:Message= response;
          const token = localStorage.getItem('token');
          if (token) {
          const decodedToken: any = jwtDecode(token);
          newMessage.sender.name = decodedToken.sub;
          }
          this.messages.push(newMessage);
          this.newMessage = '';
          setTimeout(() => {
            this.scrollToBottom();
          });
        })
      })

    }
  }

  private getMessages() {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken: any = jwtDecode(token);
      const userId = decodedToken.id;
      const ownUsername = decodedToken.sub;
      if (userId) {
        this.messageService.getMessagesByUserId(userId).subscribe((response) => {
          // Assuming response is in the format you provided
          // Map messages to users
          this.users = this.mapMessagesToUsers(response);
          this.users = this.users.filter(user => user.username !== ownUsername);
        });
      }
    }
  }

  private mapMessagesToUsers(messages: Message[]): UserDetails[] {
    const users: UserDetails[] = [];
    const usersMap: Map<string, UserDetails> = new Map();

    for (const message of messages) {
      const senderKey = message.sender.username;
      const receiverKey = message.receiver.username;

      if (!usersMap.has(senderKey)) {
        this.avatarUrl = this.image;
        usersMap.set(senderKey, {
          username: message.sender.username,
          name: message.sender.name,
          avatar: this.avatarUrl, 
          messages: []
        });
      }

      if (!usersMap.has(receiverKey)) {
        this.avatarUrl = this.image;
        usersMap.set(receiverKey, {
          username: message.receiver.username,
          name: message.receiver.name,
          avatar: this.avatarUrl, 
          messages: []
        });
      }

      const senderUser = usersMap.get(senderKey);
      if (senderUser && senderUser.messages) {
        senderUser.messages.push(message);
      }
      
      const receiverUser = usersMap.get(receiverKey);
      if (receiverUser && receiverUser.messages) {
        receiverUser.messages.push(message);
      }      

    }

    for (const user of usersMap.values()) {
      users.push(user);
    }

    for(var user of users){
      this.userService.getUserByUsername(user.username).subscribe((response)=>{
        if(response.avatarId)
        this.avatarService.downloadAvatar(response.avatarId).subscribe((url: string)=>{
          user.avatar = url;
        })
      })
    }

    return users;
  }

  private scrollToBottom(): void {
    try {
      this.messageContainer.nativeElement.scrollTop = this.messageContainer.nativeElement.scrollHeight;
    } catch (err) { }
  }
}
