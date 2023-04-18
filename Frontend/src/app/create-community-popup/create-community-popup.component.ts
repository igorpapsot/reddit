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

  createCommunity(event: any | undefined) {
      if (this.event != undefined) {
        console.log("1")
        console.log(event)
        const file:File = this.event.target.files[0];
        console.log(file);
        this.fileName = file.name;
        const formData = new FormData();

        formData.append("files", file);
        formData.append("name", this.community.name);
        formData.append("description", this.community.description);
        this.communityService.postWithPdf(formData)
        .subscribe(data => {
          console.log(data)
        })
      }

    else{
      console.log("2")
      this.communityService.post(this.community)
      .subscribe(data => {
        console.log(data)
      })
    }
  }

  community: Community = new Community();

  fileName = ''

  setEvent(event: any){
    this.event = event;
    console.log(this.event)
  }

  event: any;

}
