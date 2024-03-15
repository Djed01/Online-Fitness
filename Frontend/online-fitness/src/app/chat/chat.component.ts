import { Component, ViewChild, ElementRef } from '@angular/core';
import { Message } from '../models/message.model';
import { UserService } from '../services/user.service';
import { MessageService } from '../services/message.service';
import { jwtDecode } from 'jwt-decode';
import { AvatarService } from '../services/avatar.service';
import { UserDetails } from '../models/userDetails.model';
import { MessageRequest } from '../models/messageRequest.model';
import { forkJoin, Observable, of } from 'rxjs';
import { map, switchMap, tap } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { UserSelectionDialogComponent } from '../user-selection-dialog/user-selection-dialog.component';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  @ViewChild('messageContainer') messageContainer!: ElementRef;

  ownUsername:string = ""; 
  avatarUrl:string = "";
  users: UserDetails[] = [];
  activeUser: UserDetails | null = null;
  messages: Message[] = [];
  newMessage: string = '';
  image:string = "https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg";


  constructor(private messageService: MessageService, 
    private userService: UserService,
    private avatarService: AvatarService,
    private dialog: MatDialog) {
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
          newMessage.sender.name = this.ownUsername;
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
      this.ownUsername = decodedToken.sub;
      console.log(this.ownUsername);
      if (userId) {
        this.messageService.getMessagesByUserId(userId).subscribe((response) => {
          // Assuming response is in the format you provided
          // Map messages to users
          this.users = this.mapMessagesToUsers(response);
          //this.users = this.users.filter(user => user.username !== ownUsername);
          console.log(this.users);
        });
      }
    }
  }

  private mapMessagesToUsers(messages: Message[]):UserDetails[] {
    const users: UserDetails[] = [];
    const usersMap: Map<string, UserDetails> = new Map();
    const avatarRequests: Observable<any>[] = [];

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

    for (const [username, user] of usersMap.entries()) {
      avatarRequests.push(
        this.userService.getUserByUsername(username).pipe(
          switchMap(response => {
            if (response.avatarId) {
              return this.avatarService.downloadAvatar(response.avatarId).pipe(
                tap(url => {
                  user.avatar = url;
                })
              );
            } else {
              return of(null); // Return a placeholder if no avatarId is available
            }
          })
        )
      );
    }
  
    forkJoin(avatarRequests).subscribe(() => {
      for (const user of usersMap.values()) {
        users.push(user);
      }
    });
    
   // console.log(users);
    return users;
  }

  private scrollToBottom(): void {
    try {
      this.messageContainer.nativeElement.scrollTop = this.messageContainer.nativeElement.scrollHeight;
    } catch (err) { }
  }

  openUserSelectionDialog(): void {
    const dialogRef = this.dialog.open(UserSelectionDialogComponent, {
      data: {
        users: this.users, // Pass the users already in the inbox
        currentUsername: this.ownUsername // Pass the current user's username
      }
    });
    
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result); // Result will be the selected user
      if (result) {
        this.selectUser(result);
        this.avatarService.downloadAvatar(result.avatarId).subscribe(
          (url) => {
            result.avatar = url;
          });
        this.users.push(result);
      }
    });
  }  
}
