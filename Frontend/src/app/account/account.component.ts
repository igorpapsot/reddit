import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { ChangePasswordDTO } from '../model/changePasswordDTO';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  constructor(private userService : UserService) {
    this.account();
   }

  ngOnInit(): void {
  }

  user : User = new User();
  changePasswordDTO : ChangePasswordDTO = new ChangePasswordDTO();
  public statusPassword : string = "";
  public statusDisplayName: string = "";
  public statusDescription : string = "";

  account() {
    const response = this.userService.account();
    console.log(response);
    response.pipe().subscribe(res => {
      this.user = res;
      this.user.karma = res.karma;
    })
  }

  changePassword() {
    const response = this.userService.changePassword(this.changePasswordDTO);
    console.log(response);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if(res.toString() == 'ACCEPTED') {
        this.statusPassword = "Password changed succesfully"
      }
      else if (res.toString() == 'NOT_ACCEPTABLE') {
        this.statusPassword = "Password isnt correct"
      }
      else {
        
      }
    });
  }

  changeDescription() {
    const response = this.userService.changeDescription(this.user.description);
    console.log(response);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if(res.toString() == 'ACCEPTED') {
        this.statusDescription = 'Description changed succesfully';
      }
      else if (res.toString() == 'NOT_ACCEPTABLE') {
        this.statusDescription = 'Description not acceptable';
      }
      else {
        
      }
    });
  }

  changeDisplayName() {
    const response = this.userService.changeDisplayName(this.user.displayName);
    console.log(response);
    response.pipe().subscribe(res => {
      console.log('status: ' + res);
      if(res.toString() == 'ACCEPTED') {
        this.statusDisplayName = 'Display name changed succesfully';
      }
      else if (res.toString() == 'NOT_ACCEPTABLE') {
        this.statusDisplayName = 'Display name  not acceptable'
      }
      else {
        
      }
    });
  }

}
