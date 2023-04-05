import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PostService } from '../services/post.service';
import { Observable } from 'rxjs';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  posts!: Observable<any[]>;

  constructor(private postService : PostService, public store : StoreService) { }

  getPosts() {
    this.posts = this.postService.getPosts();
  }

  ngOnInit(): void {
    this.getPosts();
  }
  
}
