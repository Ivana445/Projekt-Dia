import { Component } from '@angular/core';
import {CreateNewButtonComponent} from "../../create-new-button/create-new-button.component";
import {ListComponent} from "../../list/list.component";
import {ListModel} from "../../../models/list.model";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-my-day-page',
  standalone: true,
  imports: [
    CreateNewButtonComponent,
    ListComponent,
    NgForOf
  ],
  templateUrl: './my-day-page.component.html',
  styleUrl: './my-day-page.component.scss'
})
export class MyDayPageComponent {

  lists: ListModel[] = [{
    name:'My TO DO list'
  }, {
    name:'My TO DO list 2'
  }, {
    name:'My TO DO list 3'
  }]
}
