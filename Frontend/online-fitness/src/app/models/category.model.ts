import { CategoryAttribute } from "./attribute.model";

export interface Category{
    id:number;
    name:string;
    status:boolean;
    categoryAttributes:CategoryAttribute[];
}