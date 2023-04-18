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
  
  desc: boolean = false;

  name: boolean = false;

  pdfText: boolean = false;

  searchF() {
    console.log(this.search.input);
    if(this.search.input ==  "" || this.search.input == undefined) {
      console.log("a")
      this.getCommunites();
    }
    else if(this.desc == true && this.name == false) {
      console.log("b")
      this.getCommunitesByDesc(this.search.input);
    }
    else if(this.name == true && this.desc == false) {
      console.log("c")
      this.getCommunitesByName(this.search.input);
    }
    else if(this.name == true && this.desc == true) {
      this.getCommunitesByNameAndDesc(this.search.input);
    }
    else if(this.pdfText == true) {
      this.getCommunitesByPdfText(this.search.input);
    }

  }

  toggleDesc() {
    if(this.pdfText != true) {
      this.desc = !this.desc
    }
  }

  toggleName() {
    if(this.pdfText != true) {
      this.name = !this.name
    }
  }

  togglePdfText() {
    this.name = false;
    this.desc = false;
    this.pdfText = !this.pdfText;
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

  getCommunitesByPdfText(text: string) {
    this.communities = this.communityService.getCommunitiesByPdfText(text);
  }

  getCommunitesByNameAndDesc(input: string) {
    this.communities = this.communityService.getCommunitiesByNameAndDesc(input);
  }

  ngOnInit(): void {
    this.getCommunites();
  }

}
  
