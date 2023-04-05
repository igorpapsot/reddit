import { Component, Input, OnInit } from '@angular/core';
import { Community } from '../model/community';
import { CommunityService } from '../services/community.service';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-community',
  templateUrl: './community.component.html',
  styleUrls: ['./community.component.css']
})
export class CommunityComponent implements OnInit {

  constructor(private communityService : CommunityService, public store : StoreService) { }

  ngOnInit(): void {
  }

  @Input()
  community : Community = new Community();

  delete() {
    this.communityService.deleteCommunity(this.community.id)
  }

  suspend() {
    this.communityService.suspendCommunity(this.community.id, this.reason);
  }

  reason : string = "";

}
