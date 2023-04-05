import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { CommunitiesComponent } from './communities/communities.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LogOutComponent } from './log-out/log-out.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: HomePageComponent},
  {path: 'communities', component: CommunitiesComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'logOut', component: LogOutComponent},
  {path: 'account', component: AccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
