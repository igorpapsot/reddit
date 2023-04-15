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

  ngOnInit(): void {
    this.getPosts();
  }

  search = new Search();

  typeOfInput: boolean = true;

  searchF() {
    console.log(this.search.input);
    if(this.search.input ==  "" || this.search.input == undefined) {
      console.log("a")
      this.getPosts();
    }
    else if(this.typeOfInput == true) {
      console.log("b")
      this.getPostsByText(this.search.input);
    }
    else if(this.typeOfInput == false) {
      console.log("c")
      this.getPostsByTitle(this.search.input);
    }
  }

  changeToText() {
    this.typeOfInput = true
  }

  changeToTitle() {
    this.typeOfInput = false
  }
  
}
