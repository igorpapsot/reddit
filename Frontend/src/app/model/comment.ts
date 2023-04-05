import { Post } from "./post";
import { User } from "./user";

export class Comment {

    id : number;
    text : string;
    isDeleted : boolean;
    user : User;
    post : Post;
    karma : number;

    constructor() {       
    }
}