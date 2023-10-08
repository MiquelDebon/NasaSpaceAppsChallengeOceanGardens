import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { HeaderComponent } from './components/auth/core/header/header.component';
import { MainComponent } from './components/auth/core/main/main.component';
import { FooterComponent } from './components/auth/core/footer/footer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ActivityFrameComponent } from './components/activities/activity-frame/activity-frame.component';
import { ActivityType1Component } from './components/activities/activity-type1/activity-type1.component';
import { ActivityType3Component } from './components/activities/activity-type3/activity-type3.component';
import { ActivityType2Component } from './components/activities/activity-type2/activity-type2.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { ModalComponent } from './shared/components/modal/modal.component';
import { ReactiveFormsModule } from '@angular/forms';
import { VideoComponent } from './components/video/video.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    FooterComponent,
    HomeComponent,
    AboutComponent,
    LoginComponent,
    RegisterComponent,
    PageNotFoundComponent,
    DashboardComponent,
    ModalComponent,
    ActivityFrameComponent,
    ActivityType1Component,
    ActivityType2Component,
    ActivityType3Component,
    VideoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DragDropModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
