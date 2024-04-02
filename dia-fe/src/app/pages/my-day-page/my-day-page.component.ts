import { Component } from '@angular/core';
import {ShowListModule} from "../../../models/show-list.module";

@Component({
  selector: 'app-my-day-page',
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
