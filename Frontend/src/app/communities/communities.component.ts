import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CommunityService } from '../services/community.service';
import { StoreService } from '../services/store.service';


@Component({
  selector: 'app-communities',
  templateUrl: './communities.component.html',
  styleUrls: ['./communities.component.css']
})
export class CommunitiesComponent implements OnInit {

  constructor(private communityService : CommunityService, public store : StoreService) { }

  communities!: Observable<any[]>;
  public search!: string; 

  searchF() {

  }

  getCommunites() {
    this.communities = this.communityService.getCommunities();
  }

  ngOnInit(): void {
    this.getCommunites();
  }

}
  
