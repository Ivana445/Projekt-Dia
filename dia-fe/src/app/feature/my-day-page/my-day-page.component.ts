import { Component } from '@angular/core';
import {CreateNewButtonComponent} from "../../create-new-button/create-new-button.component";
import {ShowListComponent} from "../../show-list/show-list.component";
import {ShowListModule} from "../../../models/show-list.module";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-my-day-page',
  standalone: true,
  imports: [
    CreateNewButtonComponent,
    ShowListComponent,
    NgForOf
  ],
  templateUrl: './my-day-page.component.html',
  styleUrl: './my-day-page.component.scss'
})
export class MyDayPageComponent {

  lists: ShowListModule[] = [{
    TODOlistname:'My TO DO list'
  }, {
    TODOlistname:'My TO DO list 2'
  }, {
    TODOlistname:'My TO DO list 3'
  }]
}
