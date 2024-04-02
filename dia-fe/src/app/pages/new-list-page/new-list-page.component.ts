import { Component } from '@angular/core';
import {ItemModel} from "../../../models/item.module";

@Component({
  selector: 'app-new-list-page',
  templateUrl: './new-list-page.component.html',
  styleUrl: './new-list-page.component.scss'
})
export class NewListPageComponent {

  items: ItemModel[] = [{
    itemname:'My TO DO list'
  }, {
    itemname:'My TO DO list 2'
  }, {
    itemname:'My TO DO list 3'
  }]

}
