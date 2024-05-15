import {Component, inject, OnInit} from '@angular/core';
import {NavigationModel} from "../../../models/navigation.model";
import {NavItemComponent} from "./nav-item/nav-item.component";
import {Router, RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";
import {LoginService} from "../../../services/client/login.service";
import {FormsModule} from "@angular/forms";
import {ListModel} from "../../../models/list.model";
import {ListService} from "../../../services/list.service";

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [NavItemComponent, RouterLink, NgForOf, FormsModule],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.scss'
})
export class NavigationComponent implements OnInit{

  private readonly router = inject(Router)


  constructor(private loginService: LoginService, private listService: ListService) {
  }

  protected nav: NavigationModel[] = [{
    routerLink: 'feature/home-page',
    nazov: 'Home',
  }, {
    routerLink: 'feature/my-day-page',
    nazov: 'My day',
  }, {
    routerLink: 'feature/calendar-page',
    nazov: 'Calendar',
  }]

  protected usernav: NavigationModel[] = [{
    routerLink: 'feature/new-list-page',
    nazov: 'New-list',
  }, {
    routerLink: 'feature/profile-page',
    nazov: 'Profile',
  }]
  protected tasks: NavigationModel[] = [{
    routerLink: 'feature/list-page',
    nazov: 'My TO DO list',
  }]

  withoutLink(){
    this.loginService.isLogged()
  }
  logoutSubmit(): void {
    this.loginService.logout().subscribe({
      next: () => this.router.navigate(['login'])
    });
  }

  allLists: ListModel[] = [];
  getAllToDoLists(){
    this.listService.getAllLists().subscribe((lists: ListModel[]) =>{
      this.allLists = lists;
    })
    console.log(this.allLists);
  }
  ngOnInit(): void {
    this.getAllToDoLists();
  }

}
