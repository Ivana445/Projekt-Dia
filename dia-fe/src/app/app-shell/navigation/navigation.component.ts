import {Component} from '@angular/core';
import {NavigationModel} from "../../../models/navigation.model";
import {NavItemComponent} from "./nav-item/nav-item.component";
import {RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [NavItemComponent, RouterLink, NgForOf],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.scss'
})
export class NavigationComponent {


  protected nav: NavigationModel[] = [{
    routerLink: 'feature/home-page',
    nazov: 'Home',
    icon: 'assets/images/navIcons/home.png'
  }, {
    routerLink: 'feature/my-day-page',
    nazov: 'My day',
    icon: 'assets/images/navIcons/todolist.png'
  }, {
    routerLink: 'feature/calendar-page',
    nazov: 'Calendar',
    icon: 'assets/images/navIcons/calendar.png'
  }]

  protected usernav: NavigationModel[] = [{
    routerLink: 'feature/new-list-page',
    nazov: 'New-list',
    icon: 'assets/images/navIcons/new-list.png'
  }, {
    routerLink: 'feature/profile-page',
    nazov: 'Profile',
    icon: 'assets/images/navIcons/profile.png'
  }]
  protected tasks: NavigationModel[] = [{
    routerLink: 'feature/new-list-page',
    nazov: 'My TO DO list',
    icon: 'assets/images/navIcons/new-list.png'
  }]
}
