import { Community } from "./community";
import { Flair } from "./flair";
import { User } from "./user";

export class Post {

    id: number;
    text: string;
    title: string;
    imagePath: string;
    userId: number;
    flair: Flair;
    communityId: string;
    karma : number;
    username: string;

    constructor() {
        
    }
}