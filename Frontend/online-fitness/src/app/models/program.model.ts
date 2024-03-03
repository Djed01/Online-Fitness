import { Image } from "./image.model";
import { CategoryAttribute } from "./attribute.model";

export interface Program{
    id: number;
    title: string;
    description:string;
    price : number;
    images : Image[];
    duration : number;
    difficulty: string;
    location: string;
    instructorName: string;
    instructorSurname: string;
    instructorContact: string;
    creationDate: Date;
    categoryName: string;
    categoryId:number;
    userId: number;
    attributes: CategoryAttribute[];
    status:boolean;
}