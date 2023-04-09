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
    communityId: number;
    karma : number;
    user: User;
    username: string;

    constructor() {
        
    }
}