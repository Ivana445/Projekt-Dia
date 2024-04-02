import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";
import {NgFor, NgOptimizedImage} from "@angular/common";
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
import {MatFormField} from "@angular/material/form-field";
import {
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerModule,
    MatDatepickerToggle
} from "@angular/material/datepicker";
import {MatInput} from "@angular/material/input";
import { ShellComponent } from './shell/shell.component';
import { TestComponent } from './test/test.component';
import { NavItemComponent } from './shell/nav-item/nav-item.component';
import {HttpClientModule} from "@angular/common/http";
import {MatList, MatListItem} from "@angular/material/list";
import {MatDivider} from "@angular/material/divider";
import { NameTimeComponent } from './name-time/name-time.component';
import { ShowListComponent } from './show-list/show-list.component';
import { ProgressCircleComponent } from './progress-circle/progress-circle.component';
import {MatChip, MatChipInput, MatChipListbox} from "@angular/material/chips";
import {MatChipInputHarness} from "@angular/material/chips/testing";
import { ItemComponent } from './item/item.component';
import {FullCalendarModule} from "@fullcalendar/angular";
import {CalendarModule} from "primeng/calendar";
import {FormsModule} from "@angular/forms";

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
        LoginPageComponent,
        ShellComponent,
        TestComponent,
        NavItemComponent,
        NameTimeComponent,
        ShowListComponent,
        ShowListComponent,
        ProgressCircleComponent,
        ItemComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FullCalendarModule,
    MatChip,
    MatChipInput,
    MatChipListbox,
    MatDatepickerModule,
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
    MatFormField,
    MatDatepickerToggle,
    MatDatepicker,
    MatInput,
    MatDatepickerInput,
    NgOptimizedImage,
    HttpClientModule,
    MatList,
    MatListItem,
    MatDivider,
    MatChip,
    MatChipInput,
    CalendarModule,
    FormsModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
