import { Component } from '@angular/core';
import {CalendarModule} from "primeng/calendar";
import {PaginatorModule} from "primeng/paginator";
import {NgClass, NgForOf} from "@angular/common";

@Component({
  selector: 'app-calendar',
  standalone: true,
    imports: [
        CalendarModule,
        PaginatorModule,
        NgClass,
        NgForOf
    ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.scss'
})
export class CalendarComponent{

  date: Date | undefined;


    lists: any[] = [
        // { name: 'List 1', deadline: '2024-04-20' },
        // { name: 'List 2', deadline: '2024-04-22' },
        // { name: 'List 3', deadline: '2024-04-25' }
    ]
    //selectedDate: Date; // proměnná pro uložení vybraného data v kalendáři
    /*lists: any[] = [
        { name: 'List 1', deadline: '2024-04-20' },
        { name: 'List 2', deadline: '2024-04-22' },
        { name: 'List 3', deadline: '2024-04-25' }
    ]; // pole pro uložení seznamů
    isDeadlineReached(date: Date): boolean {
        // projdeme všechny seznamy a zkontrolujeme, zda je některý deadline na zadaný den
        for (const list of this.lists) {
            const deadlineDate = new Date(list.deadline);
            if (date.getDate() === deadlineDate.getDate()) {
                return true;
            }
        }
        return false;
    }

     */
}
