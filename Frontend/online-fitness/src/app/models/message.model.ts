import { UserDetails } from "./userDetails.model";
export interface Message {
    id:number;
    content: string;
    date:Date;
    sender: UserDetails;
    receiver:UserDetails;
  }