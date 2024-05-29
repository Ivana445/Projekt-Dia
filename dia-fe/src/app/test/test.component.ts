import {Component, inject, OnInit} from '@angular/core';
import {FullCalendarModule} from "@fullcalendar/angular";
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import {ListService} from "../../services/list.service";
import {ListModel} from "../../models/list.model";

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [
    FullCalendarModule
  ],
  templateUrl: './test.component.html',
  styleUrl: './test.component.scss'
})
export class TestComponent implements OnInit{
  private readonly listService = inject(ListService);
  lists: ListModel[] = [];
  calendarOptions: CalendarOptions = {
    plugins: [dayGridPlugin],
    initialView: 'dayGridMonth'
  };

  ngOnInit(): void {
    this.getAllToDoLists();
  }

  getAllToDoLists() {
    this.listService.getAllLists().subscribe((lists: ListModel[]) => {
      this.lists = lists;
      console.log(this.lists);
      this.updateCalendarEvents();
    });
  }

  updateCalendarEvents() {
    const events = this.lists.map(list => ({
      title: list.name,
      start: new Date(Number(list.deadline)) // Transformujte epochálny dátum na formát Date
    }));

    this.calendarOptions = {
      events: events // Pridajte transformované udalosti zo zoznamu do events
    };
  }

}
