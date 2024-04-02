import {Component, inject} from '@angular/core';
import {Router} from "@angular/router";
import {NavigationModel} from "../models/navigation.model";
import {UserService} from "../services/user.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent {
    userService = inject(UserService);

    title = 'Project';
    nav: NavigationModel[] = [{
        routerLink: 'pages/home-page',
        nazov: 'Home',
        icon: 'assets/images/navIcons/home.png'
    }, {
        routerLink: 'pages/my-day-page',
        nazov: 'My day',
        icon: 'assets/images/navIcons/todolist.png'
    }, {
        routerLink: 'pages/calendar-page',
        nazov: 'Calendar',
        icon: 'assets/images/navIcons/calendar.png'
    }]

    usernav: NavigationModel[] = [{
        routerLink: 'pages/new-list-page',
        nazov: 'New-list',
        icon: 'assets/images/navIcons/new-list.png'
    }, {
        routerLink: 'pages/profile-page',
        nazov: 'Profile',
        icon: 'assets/images/navIcons/profile.png'
    }]
    tasks: NavigationModel[] = [{
        routerLink: 'pages/new-list-page',
        nazov: 'My TO DO list',
        icon: 'assets/images/navIcons/new-list.png'
    }]


    show(): boolean{

        return true
    }

    //private readonly service = inject(NazovServisu)

    //activeTasks$ = this.service.nazovmetody
    logout$ = this.userService.logOut('sedrfghj')

    logOut(){
        this.userService.logOut('sedrfghj').subscribe()
        console.log("odhlaseny")
    }
}
