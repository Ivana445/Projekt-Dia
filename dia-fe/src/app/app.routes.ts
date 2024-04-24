import {Routes} from '@angular/router';
import {HomePageComponent} from "./feature/home-page/home-page.component";
import {PageNotFoundComponent} from "./feature/page-not-found/page-not-found.component";
import {MyDayPageComponent} from "./feature/my-day-page/my-day-page.component";
import {CalendarPageComponent} from "./feature/calendar-page/calendar-page.component";
import {NewListPageComponent} from "./feature/new-list-page/new-list-page.component";
import {ProfilePageComponent} from "./feature/profile-page/profile-page.component";
import {LoginComponent} from "./login/login.component";


export const routes: Routes = [
  {
    path: 'feature/home-page',
    component: HomePageComponent
  },
  {
    path: 'feature/my-day-page',
    component: MyDayPageComponent
  },
  {
    path: 'feature/calendar-page',
    component: CalendarPageComponent
  },
  {
    path: 'feature/new-list-page',
    component: NewListPageComponent
  },
  {
    path: 'feature/profile-page',
    component: ProfilePageComponent
  },
  {
    path: 'path',
    component: PageNotFoundComponent
  },
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

