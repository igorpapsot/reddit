import { Component, Input, OnInit } from '@angular/core';
import { Community } from '../model/community';
import { Flair } from '../model/flair';
import { Post } from '../model/post';
import { PostService } from '../services/post.service';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private postService : PostService, public store : StoreService) { 
  }
  
  deleted : boolean = false;

  statusComments : boolean = true;

  @Input()
  public id : number = 0;

  @Input()
  public post : Post = new Post();

  @Input()
  public flair : Flair = new Flair();

  @Input()
  public community : Community = new Community();

  ngOnInit(): void {
  }

  upVote() {
    const response = this.postService.upVote(this.id);
    console.log(response);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if (res.toString() == 'OK') {
        this.post.karma = this.post.karma +1;
      }
      else if (res.toString() == 'ACCEPTED'){
        this.post.karma = this.post.karma - 1;
      }
      else {

      }
  });
  }

  downVote() {
    const response = this.postService.downVote(this.id);
    console.log(response);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if (res.toString() == 'OK') {
        this.post.karma = this.post.karma - 1;
      }
      else if (res.toString() == 'ACCEPTED'){
        this.post.karma = this.post.karma + 1;
      }
      else {

      }
  });
  }

  delete() {
    this.postService.delete(this.id);
    this.deleted = true;
  }

  showComments() {
    this.statusComments = !this.statusComments;
  }


}
