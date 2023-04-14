import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CommunityService } from '../services/community.service';
import { StoreService } from '../services/store.service';
import { Search } from '../model/search';


@Component({
  selector: 'app-communities',
  templateUrl: './communities.component.html',
  styleUrls: ['./communities.component.css']
})
export class CommunitiesComponent implements OnInit {

  constructor(private communityService : CommunityService, public store : StoreService) { }

  communities!: Observable<any[]>;

  search = new Search(); 

  searchF() {
    console.log(this.search.input);
    if(this.search.input ==  "" || this.search.input == undefined) {
      console.log("jaca")
      this.getCommunites();
    }
    else {
      console.log("prica")
      this.getCommunitesByDesc(this.search.input);
    }
  }

  getCommunites() {
    this.communities = this.communityService.getCommunities();
  }

  getCommunitesByName(name: string) {
    this.communities = this.communityService.getCommunitiesByName(name);
  }

  getCommunitesByDesc(desc: string) {
    this.communities = this.communityService.getCommunitiesByDescription(desc);
  }

  ngOnInit(): void {
    this.getCommunites();
  }

}
  
