import {Component, inject, OnInit} from '@angular/core';
import {ItemModel} from "../../../models/item.module";
import {PaginatorModule} from "primeng/paginator";
import {CalendarModule} from "primeng/calendar";
import {ItemComponent} from "../../item/item.component";
import {NgForOf} from "@angular/common";
import {CalendarComponent} from "../../calendar/calendar.component";
import {ItemService} from "../../../services/item.service";
import {ListModel} from "../../../models/list.model";
import {ListService} from "../../../services/list.service";

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
export class NewListPageComponent implements OnInit{

  private readonly itemService = inject(ItemService)
  private readonly listService = inject(ListService)


  date: Date | undefined;

  ems: ItemModel[] = [{
    name:'Item of TO DO list'
  }, {
    name:'Item of TO DO list 2'
  }, {
    name:'Item of TO DO list 3'
  }]

  tems: ItemModel[] = [
    {name:'Item of TO DO list'},
    {name:'Item of TO DO list 2'}
  ]

  ngOnInit() {
    this.addList()
  }

  items: ItemModel[] = []

  newItemName: string = '';

  addItem(){
    if (this.newItemName.trim()) { // Kontrola, ci nová položka nie je prázdna
      const newItem :ItemModel = {name: this.newItemName};
      this.itemService.postItem(this.newList,newItem).subscribe(() => {
        this.newItemName = '';
      })
    }
  }

  showItem(){
    //this.itemService.getItem()
  }

  newListName: string = '';
  newList: ListModel = { name: this.newListName};
  newListDeadline: Date | undefined;
  addList(){
    if (this.newListName.trim()) {
      this.listService.postList(this.newList).subscribe(
          //() => {this.newListName = '';}
      )
    }
  }

  updateListName(){
    this.listService.putList(this.newList).subscribe()
  }
}
