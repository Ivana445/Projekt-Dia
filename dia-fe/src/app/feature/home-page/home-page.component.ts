import {Component, OnInit} from '@angular/core';
import {NameTimeComponent} from "../../name-time/name-time.component";
import {ListModel} from "../../../models/list.model";
import {CreateNewButtonComponent} from "../../create-new-button/create-new-button.component";
import {ListComponent} from "../../list/list.component";
import {ProgressCircleComponent} from "../../progress-circle/progress-circle.component";
import {NgForOf} from "@angular/common";
import {CalendarComponent} from "../../calendar/calendar.component";
import {ListService} from "../../../services/list.service";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    NameTimeComponent,
    CreateNewButtonComponent,
    ListComponent,
    ProgressCircleComponent,
    NgForOf,
    CalendarComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit{

  constructor() {
  }

  private readonly listService = ListService

  lists: ListModel[] = [{
    name:'My TO DO list'
  }, {
    name:'My TO DO list 2'
  }, {
    name:'My TO DO list 3'
  }]

  ngOnInit() {
    //this.listService.getList()
  }


  percentage: string= '100%'
}