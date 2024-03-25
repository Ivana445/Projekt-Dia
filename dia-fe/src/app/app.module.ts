import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";
import {NgFor} from "@angular/common";
import { HomePageComponent } from './pages/home-page/home-page.component';
import {MatMenu, MatMenuItem, MatMenuTrigger} from "@angular/material/menu";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import { MyDayPageComponent } from './pages/my-day-page/my-day-page.component';
import { CalendarPageComponent } from './pages/calendar-page/calendar-page.component';
import { NewListPageComponent } from './pages/new-list-page/new-list-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { CalendarComponent } from './calendar/calendar.component';
import {MatCard} from "@angular/material/card";
import { CreateNewButonComponent } from './create-new-buton/create-new-buton.component';
import { LoginPageComponent } from './login-page/login-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    MyDayPageComponent,
    CalendarPageComponent,
    NewListPageComponent,
    ProfilePageComponent,
    CalendarComponent,
    CreateNewButonComponent,
    LoginPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatSidenav,
    MatSidenavContainer,
    MatSidenavContent,
    NgFor,
    MatMenu,
    MatMenuTrigger,
    MatIcon,
    MatMenuItem,
    MatIconButton,
    MatCard,
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
