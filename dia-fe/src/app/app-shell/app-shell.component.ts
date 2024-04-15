import {Component, inject, Input} from '@angular/core';
import {SidebarModule} from "primeng/sidebar";
import {ButtonModule} from "primeng/button";
import {NavigationService} from "../../services/client/navigation.service";

@Component({
  selector: 'app-app-shell',
  standalone: true,
  imports: [
    SidebarModule,
    ButtonModule,
  ],
  templateUrl: './app-shell.component.html',
  styleUrl: './app-shell.component.scss'
})
export class AppShellComponent {
  protected isSidebarOpen: boolean = false;
  protected isNavbarOpen: boolean = false;
  private readonly navigationService = inject(NavigationService)

  @Input()
  navbarWidth = '100'

  @Input()
  sidebarWidth = '100'


  constructor() {
    this.navigationService.sidebarOpen$.subscribe(open => this.isSidebarOpen = open);
    this.navigationService.navbarOpen$.subscribe(open => this.isNavbarOpen = open);
  }
}
