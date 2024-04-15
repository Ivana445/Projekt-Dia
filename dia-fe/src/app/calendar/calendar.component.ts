import { Component } from '@angular/core';
import {CalendarModule} from "primeng/calendar";
import {PaginatorModule} from "primeng/paginator";

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [
    CalendarModule,
    PaginatorModule
  ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.scss'
})
export class CalendarComponent{

  date: Date | undefined;
}
