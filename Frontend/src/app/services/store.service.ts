import { Injectable } from '@angular/core';
import jwt_decode, { JwtPayload } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor() { 
    if(sessionStorage.getItem('token')){
      var t = sessionStorage.getItem('token');
      this.token = this.getDecodedAccessToken(t);
      this.loginStatus = true;
      this.role = this.token.role.authority;
      this.username = this.token.sub;
      this.id = this.token.id;
      console.log(this.id);
    }
  }

  public loginStatus = false;

  public token : any;

  public role : string = "";

  public username : string = "";

  public id : number = 0;

  setLoginStatus(status: boolean) {
    this.loginStatus = status;
  }

  setToken(token: any) {
    this.token = token;
    this.role = token.role.authority;
    this.username = token.sub;
    this.id = token.id;
    console.log(this.username);
    console.log(this.role);
  }

  getDecodedAccessToken(token: any): any {
    try {
      return jwt_decode(token);
    } catch(Error) {
      console.log(Error);
      return null;
    }
  }

}
