import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StoreService } from '../services/store.service';

@Component({
  selector: 'app-log-out',
  templateUrl: './log-out.component.html',
  styleUrls: ['./log-out.component.css']
})
export class LogOutComponent implements OnInit {

  constructor(public store : StoreService, private router : Router) { 
    this.logOut();
  }

  ngOnInit(): void {
  }

  logOut() {
    sessionStorage.removeItem('token');
    this.store.loginStatus = false;
    this.router.navigateByUrl("/");
  }

}
