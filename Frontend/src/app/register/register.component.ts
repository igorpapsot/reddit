import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private userService : UserService, private router : Router) { }

  ngOnInit(): void {
  }

  register() {
    this.userService.register(this.user)
    .subscribe(data => {
      console.log(data)
    })
    this.router.navigate(['/login']);
  }

  user = new User();
}
