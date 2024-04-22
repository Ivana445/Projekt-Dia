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
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-new-list-page',
  standalone: true,
  imports: [
    PaginatorModule,
    CalendarModule,
    ItemComponent,
    NgForOf,
    CalendarComponent,
    ReactiveFormsModule
  ],
  templateUrl: './new-list-page.component.html',
  styleUrl: './new-list-page.component.scss'
})
export class NewListPageComponent implements OnInit{

  private readonly itemService = inject(ItemService)
  private readonly listService = inject(ListService)

  deadline= new FormGroup({
  date: new FormControl
  });

  items: ItemModel[] = []

  newItemName: string = '';

  newListName: string = '';
  newList: ListModel = { name: this.newListName, deadLine: this.deadline.controls.date.value};

  ngOnInit() {
    this.addList()
  }

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


  addList(){
    if (this.newListName.trim() && this.deadline) {
      this.listService.postList(this.newList).subscribe(
          //() => {this.newListName = '';}
      )
    }
  }

  updateListName(){
    this.listService.putList(this.newList).subscribe()
  }

  deleteList(){
    console.log('vymazany list')
    this.listService.deleteList(this.newList)
  }
}
