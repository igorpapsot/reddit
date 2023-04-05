import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError, Observable } from 'rxjs';
import { Community } from '../model/community';
import { Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {

  constructor(private http : HttpClient) { }

  httpOptions() {
    return  {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
        "Access-Control-Allow-Methods": 'GET,POST,PATCH,DELETE,PUT,OPTIONS'
      })
    };
  }

  optionsSuspend() {
    return  {
      headers: new HttpHeaders({
        'Content-Type':  'text/plain',
        'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
        'Access-Control-Allow-Methods': 'GET,POST,DELETE,PUT'
      })
    };
  }

  communities! : Observable<any[]>;

  getCommunities() : Observable<any[]> {
    return this.http.get<any[]>(environment.ROOT_URL + "communities");
  }

  deleteCommunity(id : number) {
    this.http.delete(environment.ROOT_URL + "communities/" + id, this.httpOptions())
    .subscribe(() => this.status = 'Delete successful');
    console.log(this.status);
  }

  suspendCommunity(id : number, reason : String) {
    console.log(reason);
    this.http.put<any>(environment.ROOT_URL + "communities/" + id + "/suspend", reason, this.optionsSuspend())
    .subscribe(() => this.suspendStatus = 'Delete successful');
    console.log(this.suspendStatus);
  }

  post(community : Community) : Observable<Community>{
    const body=JSON.stringify(community);
    console.log(body)
    return this.http.post<Community>(environment.ROOT_URL + "communities", body, this.httpOptions())
    .pipe(
      catchError((err) => {
        console.error(err);
        throw err;
      }) 
    )
  }

  suspendStatus : string = ""
  status : string = "";
}
