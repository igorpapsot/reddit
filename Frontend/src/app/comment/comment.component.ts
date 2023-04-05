import { Component, Input, OnInit } from '@angular/core';
import { Comment } from '../model/comment';
import { CommentService } from '../services/comment.service';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input()
  comment : Comment = new Comment();

  name : string = "";

  editStatus : boolean = true;

  deleted : boolean = false;

  constructor(public store : StoreService, private commentService : CommentService) { }

  ngOnInit(): void {
    if(this.comment.user.displayName == null) {
      this.name = this.comment.user.username;
    }
    else {
      this.name = this.comment.user.displayName;
    }
  }

  delete() {
    const response = this.commentService.deleteComment(this.comment.post.id, this.comment.id);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if (res.toString() == 'OK') {
       console.log(res);
       this.deleted = true;
      }
      else {
        console.log(res);
      }
    })
  }


  edit() {
    this.editStatus = !this.editStatus;
  }

  editComment() {
    const response = this.commentService.editComment(this.comment.post.id, this.comment.id, this.comment.text)
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if (res.toString() == 'OK') {
       console.log(res);
      }
      else {
        console.log(res);
      }
    })
  }

  upVote() {
    const response = this.commentService.upVote(this.comment.post.id, this.comment.id);
    console.log(response);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if (res.toString() == 'OK') {
       console.log(res);
       this.comment.karma = this.comment.karma + 1;
      }
      else {
        console.log(res);
        this.comment.karma = this.comment.karma - 1;
      }
    })
  }

  downVote() {
    const response = this.commentService.downVote(this.comment.post.id, this.comment.id);
    console.log(response);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if (res.toString() == 'OK') {
       console.log(res);
       this.comment.karma = this.comment.karma - 1;
      }
      else {
        console.log(res);
        this.comment.karma = this.comment.karma + 1;
      }
    })
  }

}
