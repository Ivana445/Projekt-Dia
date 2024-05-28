import {Component, OnInit} from '@angular/core';
import {CalendarComponent} from "../../calendar/calendar.component";
import {ListComponent} from "../../list/list.component";
import {CreateNewButtonComponent} from "../../create-new-button/create-new-button.component";
import {TestComponent} from "../../test/test.component";

@Component({
  selector: 'app-calendar-page',
  standalone: true,
  imports: [
    CalendarComponent,
    ListComponent,
    CreateNewButtonComponent,
    TestComponent
  ],
  templateUrl: './calendar-page.component.html',
  styleUrl: './calendar-page.component.scss'
})
export class CalendarPageComponent implements OnInit{

  ngOnInit() {
    //window.location.reload();
  }
}
