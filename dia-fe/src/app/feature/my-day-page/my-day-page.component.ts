import {Component, inject, OnInit} from '@angular/core';
import {CreateNewButtonComponent} from "../../create-new-button/create-new-button.component";
import {ListComponent} from "../../list/list.component";
import {ListModel} from "../../../models/list.model";
import {NgForOf, NgIf} from "@angular/common";
import {ListService} from "../../../services/list.service";

@Component({
  selector: 'app-my-day-page',
  standalone: true,
  imports: [
    CreateNewButtonComponent,
    ListComponent,
    NgForOf,
    NgIf
  ],
  templateUrl: './my-day-page.component.html',
  styleUrl: './my-day-page.component.scss'
})
export class MyDayPageComponent implements OnInit{

  private readonly listService = inject(ListService);

  lists: ListModel[] = []

  ngOnInit(): void {
    this.getAllToDoLists();
  }
  getAllToDoLists() {
    this.listService.getAllLists().subscribe((lists: ListModel[]) => {
      this.lists = lists.filter(list => list.deadline && this.isToday(new Date(list.deadline)));
      console.log(this.lists);
    });
  }

  isToday(date: Date): boolean {
    const today = new Date();
    return date.getDate() === today.getDate() &&
        date.getMonth() === today.getMonth() &&
        date.getFullYear() === today.getFullYear();
  }
}
