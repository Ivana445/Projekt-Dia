import {Component, inject} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Project';



  //private readonly service = inject(NazovServisu)

  //activeTasks$ = this.service.nazovmetody
}
