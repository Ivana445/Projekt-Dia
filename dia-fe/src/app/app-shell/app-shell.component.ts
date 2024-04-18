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
  protected isSidebarOpen: boolean = false;
  protected isNavbarOpen: boolean = false;
  private readonly navigationService = inject(NavigationService)

  private readonly loginService = inject(LoginService)

  @Input()
  navbarWidth = '100'

  @Input()
  sidebarWidth = '100'


  constructor() {
    this.navigationService.sidebarOpen$.subscribe(open => this.isSidebarOpen = open);
    this.navigationService.navbarOpen$.subscribe(open => this.isNavbarOpen = open);
  }

  auth(): LoginService {
    return this.loginService;
  }
}
