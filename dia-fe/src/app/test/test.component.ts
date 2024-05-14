import {Component, inject} from '@angular/core';
import {NavigationService} from "../../services/client/navigation.service";
import {FullCalendarModule} from "@fullcalendar/angular";
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';

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
  // premenna dedadline a name aby  vsetky listy nacitalo do kalendara
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, interactionPlugin],
    dateClick: (arg) => this.handleDateClick({arg: arg}),
    events: [
      { title: 'event 1', date: '2024-05-01' }
    ]
  };

  handleDateClick({arg}: { arg: any }) {
    alert('date click! ' + arg.dateStr)
  }

}
