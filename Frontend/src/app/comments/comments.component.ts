import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../model/post';
import { CommentService } from '../services/comment.service';
import { StoreService } from '../services/store.service';
import { Comment } from '../model/comment';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  @Input()
  post: Post = new Post();

  comments!: Observable<any[]>;

  comment : Comment = new Comment();

  constructor(private commentService : CommentService, public store : StoreService) { }

  getComments() {
    this.comments = this.commentService.getComments(this.post.id);
  }

  ngOnInit(): void {
    this.getComments();
  }

  postComment() {
    const response = this.commentService.postComment(this.post.id, this.comment);
    console.log(response);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if(res.toString() == 'ACCEPTED') {
        console.log("Password changed succesfully");
      }
      else if (res.toString() == 'NOT_ACCEPTABLE') {
        console.log("Password isnt correct");
      }
      else {
        
      }
    });
  }
} 
