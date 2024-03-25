import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-new-buton',
  templateUrl: './create-new-buton.component.html',
  styleUrl: './create-new-buton.component.scss'
})
export class CreateNewButonComponent {

  constructor(private router: Router){ }

  goToVotes($myParam: string = ''): void {
    const navigationDetails: string[] = ['pages/'];
    if($myParam.length) {
      navigationDetails.push($myParam);
    }
    this.router.navigate(navigationDetails);
  }
}
