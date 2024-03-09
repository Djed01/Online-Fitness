import { Message } from "./message.model";

export interface UserDetails {
    username: string;
    name: string;
    avatar: string;
    messages?: Message[]; 
  }