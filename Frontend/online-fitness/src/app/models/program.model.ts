import { Image } from "./image.model";
import { ProgramAttribute } from "./programAttribute.model";

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
    userId: number;
    attributes: ProgramAttribute[];
}