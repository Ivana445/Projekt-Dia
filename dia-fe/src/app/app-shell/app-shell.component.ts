import {Component, inject, Input} from '@angular/core';
import {SidebarModule} from "primeng/sidebar";
import {ButtonModule} from "primeng/button";
import {NavigationService} from "../../services/client/navigation.service";
import {LoginService} from "../../services/client/login.service";
import {NgIf} from "@angular/common";

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
    this.navigationService.navbarOpen$.subscribe(open => this.isNavbarOpen = true);
    this.show = this.auth().isLogged();
    if (this.show){
      //todo ukaz menu ak je uzivatel prihlaseny
    }
  }

  auth(): LoginService {
    return this.loginService;
  }

}
