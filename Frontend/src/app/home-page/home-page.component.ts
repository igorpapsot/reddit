import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PostService } from '../services/post.service';
import { Observable } from 'rxjs';
import { StoreService } from '../services/store.service';
import { Search } from '../model/search';

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

  getPostsByTitle(title: string) {
    this.posts = this.postService.getPostsByTitle(title);
  }

  getPostsByText(text: string) {
    this.posts = this.postService.getPostsByText(text);
  }

  getPostsByTextAndTitle(input: string) {
    this.posts = this.postService.getPostsByTextAndTitle(input);
  }

  ngOnInit(): void {
    this.getPosts();
  }

  search = new Search();

  title: boolean = false;

  text: boolean = false;

  searchF() {
    console.log(this.search.input);
    if(this.search.input ==  "" || this.search.input == undefined) {
      console.log("a")
      this.getPosts();
    }
    else if(this.text == true && this.title == false) {
      console.log("b")
      this.getPostsByText(this.search.input);
    }
    else if(this.title == true && this.text == false) {
      console.log("c")
      this.getPostsByTitle(this.search.input);
    }
    else if(this.text == true && this.title == true) {
      this.getPostsByTextAndTitle(this.search.input);
    }
  }

  toggleText() {
    this.text = !this.text;
  }

  toggleTitle() {
    this.title = !this.title;
  }
  
}
