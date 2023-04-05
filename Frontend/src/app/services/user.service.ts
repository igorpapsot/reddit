import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Jwt } from '../model/jwt';
import { ChangePasswordDTO } from '../model/changePasswordDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      //Authorization: 'my-auth-token'
    })
  };

  httpOptionsLogin = {
    headers: new HttpHeaders({
      'Content-Type':  'text/plain',
      //Authorization: 'my-auth-token'
      'responseType': 'text' as 'json'
      
    })
  };

  options() {
    return  {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
        'Access-Control-Allow-Methods': 'GET,POST,DELETE,PUT'
      })
    };
  }

  
  optionsChange() {
    return  {
      headers: new HttpHeaders({
        'Content-Type':  'text/plain',
        'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
        'Access-Control-Allow-Methods': 'GET,POST,DELETE,PUT'
      })
    };
  }

  register(user : User) : Observable<User>{
    const body=JSON.stringify(user);
    console.log(body)
    return this.http.post<User>(environment.ROOT_URL + "users", body, this.httpOptions)
    .pipe(
      catchError((err) => {
        console.error(err);
        throw err;
      }) 
      )
  }

  login(user : User) : Observable<Jwt>{
    const body=JSON.stringify(user);
    console.log(body);
    return this.http.post<Jwt>(environment.ROOT_URL + "users/login", body, this.httpOptions)
  }

  changePassword(changePassword : ChangePasswordDTO) :  Observable<any> {
    const body=JSON.stringify(changePassword);
    console.log(body);

    return this.http.put(environment.ROOT_URL +"users/changePassword", body,  this.options());
  }

  changeDisplayName(displayName : string) :  Observable<any> {
    console.log(displayName);
    return this.http.put(environment.ROOT_URL +"users/displayName", displayName,  this.optionsChange());
  }

  changeDescription(description : string) :  Observable<any> {
    console.log(description);
    return this.http.put(environment.ROOT_URL +"users/description", description,  this.optionsChange());
  }
  
  account() : Observable<User>{
    return this.http.get<User>(environment.ROOT_URL + "users/account", this.options())
  }
  
}
