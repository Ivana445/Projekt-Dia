import { Component } from '@angular/core';
import {CalendarComponent} from "../../calendar/calendar.component";
import {ShowListComponent} from "../../show-list/show-list.component";
import {CreateNewButtonComponent} from "../../create-new-button/create-new-button.component";

@Component({
  selector: 'app-calendar-page',
  standalone: true,
  imports: [
    CalendarComponent,
    ShowListComponent,
    CreateNewButtonComponent
  ],
  templateUrl: './calendar-page.component.html',
  styleUrl: './calendar-page.component.scss'
})
export class CalendarPageComponent {

}
