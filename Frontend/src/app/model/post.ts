import { Community } from "./community";
import { Flair } from "./flair";
import { User } from "./user";

export class Post {

    id: number;
    text: string;
    title: string;
    imagePath: string;
    user: User;
    flair: Flair;
    community: Community;
    karma : number;

    constructor() {
        
    }
}