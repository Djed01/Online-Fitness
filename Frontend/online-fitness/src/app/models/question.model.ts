import { Program } from "./program.model";
import { User} from "./user.model";

export interface Question{
    content:string;
    date:Date;
    userId:number;
    programId:number;
}