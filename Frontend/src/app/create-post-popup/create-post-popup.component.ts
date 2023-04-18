import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Community } from '../model/community';
import { Flair } from '../model/flair';
import { Post } from '../model/post';
import { User } from '../model/user';
import { CommunityService } from '../services/community.service';
import { FlairService } from '../services/flair.service';
import { PostService } from '../services/post.service';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-create-post-popup',
  templateUrl: './create-post-popup.component.html',
  styleUrls: ['./create-post-popup.component.css'],
})
export class CreatePostPopupComponent implements OnInit {

  closeResult!: string;

  constructor(private modalService: NgbModal, private postService : PostService, private communityService : CommunityService, private flairService : FlairService, private storeService : StoreService) { }

  ngOnInit(): void {
    this.get();
  }

  openLg(content : any) {
    this.modalService.open(content, { size: 'lg' });
  }

  get() {
    this.communities = this.communityService.getCommunities();
    this.flairs = this.flairService.getFlairs();
  }


  createPost(event: any | undefined) {
    if(this.event != undefined) {
      console.log("1")
      console.log(event)
      const file:File = this.event.target.files[0];
      console.log(file);
      this.fileName = file.name;
      const formData = new FormData();

      formData.append("files", file);
      formData.append("title", this.post.title);
      formData.append("text", this.post.text);
      formData.append("flairId", String(this.flair.id));
      formData.append("userId", String(this.storeService.id));
      formData.append("communityId", this.community.id);
      this.postService.postWithPdf(formData)
      .subscribe(data => {
        console.log(data)
      })

    }
    else{
            console.log("2")
      //this.post.username = this.storeService.username;
      this.post.userId = this.storeService.id;
      this.post.communityId = this.community.id;
      this.post.flair = this.flair;

      this.postService.post(this.post)
      .subscribe(data => {
        console.log(data)
      })
    }
   


    
  }

  flair: Flair = new Flair();

  community: Community = new Community();

  user: User = new User();

  post: Post = new Post();

  communities! : Observable<any[]>;

  flairs! : Observable<any[]>;

  fileName = ''

  setEvent(event: any){
    this.event = event;
    console.log(this.event)
  }

  event: any;


}
