import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-new-button',
  standalone: true,
  imports: [],
  templateUrl: './create-new-button.component.html',
  styleUrl: './create-new-button.component.scss'
})
export class CreateNewButtonComponent {

  constructor(private router: Router){ }

  goToVotes($myParam: string = ''): void {
    const navigationDetails: string[] = ['feature/'];
    if($myParam.length) {
      navigationDetails.push($myParam);
    }
    this.router.navigate(navigationDetails);
  }
}
