import { Component } from '@angular/core';
import {ProgressCircleComponent} from "../../progress-circle/progress-circle.component";
import {NameTimeComponent} from "../../name-time/name-time.component";

@Component({
  selector: 'app-profile-page',
  standalone: true,
  imports: [
    ProgressCircleComponent,
    NameTimeComponent
  ],
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.scss'
})
export class ProfilePageComponent {

}
