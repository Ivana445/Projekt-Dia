import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  private navbarOpenSubj = new BehaviorSubject<boolean>(true);
  navbarOpen$: Observable<boolean> = this.navbarOpenSubj.asObservable();

  constructor() {
  }

  toggleNavbar(force?: boolean): void {
    if (force != undefined) {
      this.navbarOpenSubj.next(force);
    }else {
      this.navbarOpenSubj.next(!this.navbarOpenSubj.value);
    }
  }
}
