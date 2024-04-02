import { Component } from '@angular/core';
import {DatePipe} from "@angular/common";
import {NavigationModel} from "../../../models/navigation.model";
import {ShowListModule} from "../../../models/show-list.module";
import {PercentageModule} from "../../../models/percentage.module";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent {

  lists: ShowListModule[] = [{
    TODOlistname:'My TO DO list'
  }, {
    TODOlistname:'My TO DO list 2'
  }, {
    TODOlistname:'My TO DO list 3'
  }]

  percentage: string= '100%'

  //constructor(private datePipe: DatePipe) { }
  //time = this.datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm:ss');

}
