import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {MyDayPageComponent} from "./pages/my-day-page/my-day-page.component";
import {CalendarPageComponent} from "./pages/calendar-page/calendar-page.component";
import {NewListPageComponent} from "./pages/new-list-page/new-list-page.component";
import {ProfilePageComponent} from "./pages/profile-page/profile-page.component";

const routes: Routes = [
  {path: 'pages/home-page',component:HomePageComponent},
  {path: 'pages/my-day-page',component:MyDayPageComponent},
  {path: 'pages/calendar-page',component:CalendarPageComponent},
  {path: 'pages/new-list-page',component:NewListPageComponent},
  {path: 'pages/profile-page',component:ProfilePageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
