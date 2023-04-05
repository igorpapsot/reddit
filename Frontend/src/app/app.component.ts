import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { StoreService } from './services/store.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(public store : StoreService){

  }
}
