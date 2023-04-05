import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Jwt } from '../model/jwt';
import { User } from '../model/user';
import { StoreService } from '../services/store.service';
import { UserService } from '../services/user.service';
import jwt_decode, { JwtPayload } from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService :UserService, private storeSevice : StoreService, private router : Router) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.login(this.user)
    .subscribe(jwt => {
      console.log(jwt);
      sessionStorage.clear;
      sessionStorage.removeItem('token');
      sessionStorage.setItem('token', jwt.jwt);
      this.storeSevice.setToken(this.getDecodedAccessToken(jwt.jwt));

      this.storeSevice.setLoginStatus(true);
      this.router.navigateByUrl("/");
    },
    err => this.loginStatus = "Wrong credentials"
    );
    
    //console.log(this.jwt.jwt + "sasasasasasas");
    // if(token != null) {
    //   var token = 
    // }
    // console.log(token);
    //this.storeSevice.setToken(token);
    
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch(Error) {
      console.log(Error);
      return null;
    }
  }

  submitted = false;

  loginStatus : string = "";

  jwt : Jwt = new Jwt();

  user = new User();

}
