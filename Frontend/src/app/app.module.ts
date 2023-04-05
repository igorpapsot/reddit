import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AlertModule } from 'ngx-bootstrap/alert';
import { HomePageComponent } from './home-page/home-page.component';
import { CommunitiesComponent } from './communities/communities.component';
import { HttpClientModule } from '@angular/common/http';
import { PostComponent } from './post/post.component';
import { CreatePostPopupComponent } from './create-post-popup/create-post-popup.component';
import { CommunityComponent } from './community/community.component';
import { UpdatePostPopupComponent } from './update-post-popup/update-post-popup.component';
import { CreateCommunityPopupComponent } from './create-community-popup/create-community-popup.component';
import { LogOutComponent } from './log-out/log-out.component';
import { AccountComponent } from './account/account.component';
import { CommentsComponent } from './comments/comments.component';
import { CommentComponent } from './comment/comment.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomePageComponent,
    CommunitiesComponent,
    PostComponent,
    CreatePostPopupComponent,
    CommunityComponent,
    UpdatePostPopupComponent,
    CreateCommunityPopupComponent,
    LogOutComponent,
    AccountComponent,
    CommentsComponent,
    CommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AlertModule.forRoot(),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
