import { Component } from '@angular/core';
import {ItemModel} from "../../../models/item.module";
import {provideLuxonDateAdapter} from "@angular/material-luxon-adapter";

@Component({
  selector: 'app-new-list-page',
  templateUrl: './new-list-page.component.html',
  styleUrl: './new-list-page.component.scss',
})
export class NewListPageComponent {
  startDate = new Date(1990, 0, 1);

  items: ItemModel[] = [{
    itemname:'Item of TO DO list'
  }, {
    itemname:'Item of TO DO list 2'
  }, {
    itemname:'Item of TO DO list 3'
  }]

}
