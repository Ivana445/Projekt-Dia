import {Component, inject} from '@angular/core';
import {NavigationService} from "../../services/client/navigation.service";
import {FullCalendarModule} from "@fullcalendar/angular";
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [
    FullCalendarModule
  ],
  templateUrl: './test.component.html',
  styleUrl: './test.component.scss'
})
export class TestComponent {
  calendarOptions: CalendarOptions = {
    plugins: [dayGridPlugin],
    initialView: 'dayGridMonth',
    weekends: false,
    events: [
      { title: 'Meeting', start: '2024-04-04T12:30:00-05:00' }
    ]
  };
  navigationService = inject(NavigationService)

  toggleNavbar() {
    this.navigationService.toggleNavbar()
  }

  toggleSidebar() {
    this.navigationService.toggleSidebar()
  }
}
