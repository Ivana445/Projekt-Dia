import {Component, inject, Input} from '@angular/core';
import {SidebarModule} from "primeng/sidebar";
import {ButtonModule} from "primeng/button";
import {NavigationService} from "../../services/client/navigation.service";
import {LoginService} from "../../services/client/login.service";
import {NgIf} from "@angular/common";
import {NavigationComponent} from "./navigation/navigation.component";

@Component({
  selector: 'app-app-shell',
  standalone: true,
  imports: [
    SidebarModule,
    ButtonModule,
    NgIf,
  ],
  templateUrl: './app-shell.component.html',
  styleUrl: './app-shell.component.scss'
})
export class AppShellComponent {
  protected isNavbarOpen: boolean = true ;
  private readonly navigationService = inject(NavigationService)

  private readonly loginService = inject(LoginService)


  @Input()
  navbarWidth = '100'

  @Input()
  sidebarWidth = '100'

  show: boolean = false;


  constructor() {
    this.navigationService.navbarOpen$.subscribe(open => this.isNavbarOpen = false);
    this.show = this.auth().isLogged();
    console.log(this.show)
    if (this.show){
      //todo ukaz menu ak je uzivatel prihlaseny
      this.navigationService.navbarOpen$.subscribe(open => this.isNavbarOpen = true);
    }
  }

  auth(): LoginService {
    return this.loginService;
  }

}
