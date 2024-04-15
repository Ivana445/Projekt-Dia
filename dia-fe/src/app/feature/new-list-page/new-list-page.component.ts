import { Component } from '@angular/core';
import {ItemModel} from "../../../models/item.module";
import {PaginatorModule} from "primeng/paginator";
import {CalendarModule} from "primeng/calendar";
import {ItemComponent} from "../../item/item.component";
import {NgForOf} from "@angular/common";
import {CalendarComponent} from "../../calendar/calendar.component";
import {ItemService} from "../../../services/item.service";

@Component({
  selector: 'app-new-list-page',
  standalone: true,
  imports: [
    PaginatorModule,
    CalendarModule,
    ItemComponent,
    NgForOf,
    CalendarComponent
  ],
  templateUrl: './new-list-page.component.html',
  styleUrl: './new-list-page.component.scss'
})
export class NewListPageComponent {

  private readonly itemService = ItemService

  date: Date | undefined;

  tems: ItemModel[] = [{
    itemname:'Item of TO DO list'
  }, {
    itemname:'Item of TO DO list 2'
  }, {
    itemname:'Item of TO DO list 3'
  }]

  items: ItemModel[] = [
    {itemname:'Item of TO DO list'},
    {itemname:'Item of TO DO list 2'}
  ]
  addItem(newItem: string):ItemModel{
    if (newItem.trim() !== '') { // Kontrola, zda nová položka není prázdná
      //this.items.push(newItem);
      //this.itemService.postItem()
    }
    // @ts-ignore
    return
  }
}
