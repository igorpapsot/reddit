import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Community } from '../model/community';
import { Flair } from '../model/flair';
import { Post } from '../model/post';
import { User } from '../model/user';
import { CommunityService } from '../services/community.service';
import { FlairService } from '../services/flair.service';
import { PostService } from '../services/post.service';

@Component({
  selector: 'app-create-community-popup',
  templateUrl: './create-community-popup.component.html',
  styleUrls: ['./create-community-popup.component.css']
})
export class CreateCommunityPopupComponent implements OnInit {

  closeResult!: string;

  constructor(private modalService: NgbModal, private communityService : CommunityService) { }

  ngOnInit(): void {
  }

  openLg(content : any) {
    this.modalService.open(content, { size: 'lg' });
  }

  createCommunity() {
    this.communityService.post(this.community)
    .subscribe(data => {
      console.log(data)
    })
  }

  community: Community = new Community();

}
