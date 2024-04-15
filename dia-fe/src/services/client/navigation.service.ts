import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NavigationService {
  private sidebarOpenSubj = new BehaviorSubject<boolean>(true);
  sidebarOpen$: Observable<boolean> = this.sidebarOpenSubj.asObservable();
  private navbarOpenSubj = new BehaviorSubject<boolean>(true);
  navbarOpen$: Observable<boolean> = this.navbarOpenSubj.asObservable();

  constructor() {
  }

  toggleSidebar(force?: boolean): void {
    if (force != undefined) {
      this.sidebarOpenSubj.next(force);
    }else {
      this.navbarOpenSubj.next(!this.navbarOpenSubj.value);
    }
  }

  toggleNavbar(force?: boolean): void {
    if (force != undefined) {
      this.navbarOpenSubj.next(force);
    }else {
      this.navbarOpenSubj.next(!this.navbarOpenSubj.value);
    }
  }
}
