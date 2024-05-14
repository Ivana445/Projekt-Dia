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
import {LoginService} from "../../../services/client/login.service";

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
  private readonly loginService = inject(LoginService)

  deadline= new FormGroup({
  date: new FormControl
  });

  items: ItemModel[] = []

  newItemName: string = '';

  newListName: string = '';
  newList: ListModel = { name: '', deadline: undefined};


  token = this.loginService.getToken();

  ngOnInit() {
    this.addList()

  }

  addItem(){
    if (this.newItemName.trim()) { // Kontrola, ci nová položka nie je prázdna
      const newItem :ItemModel = {name: this.newItemName};
      console.log(newItem)
      if (this.token != null) {
        this.itemService.postItem(this.newList, newItem).subscribe(() => {
          this.newItemName = '';
        })
      }
    }
  }

  showItem(){
    //this.itemService.getItem()
  }


  addList() {
    if (this.newListName.trim() && this.deadline.controls.date.value) {
      // Predpokladám, že token je uložený v nejakej premennej token
      this.newList.name = this.newListName;
      this.newList.deadline = this.deadline.controls.date.value
      console.log(this.newList)
      console.log(this.token , 'token pri pridani listu')
      if (this.token !== null) {
        console.log('presiel som dnu', this.token)
        this.listService.postList(this.newList, this.token).subscribe({
          next: () => {
            this.newListName = '';
          },
          error: (err) =>{
            console.error('chyba pri pridani listu', err);
            console.log('token', this.token)
          }
        }

        );
      }
    }
  }

  updateListName(){
    if(this.newListName.trim()){
      if (this.token !== null){
        this.listService.putList(this.newList, this.token).subscribe({
          next: () => {

          },
          error: (err) => {
            console.error('chyba pri update listu', err)
        }
        })
      }
    }
  }

  deleteList(){
    if (this.newList.id != null && this.token != null) {
      this.listService.deleteList(this.newList.id, this.token).subscribe({
        next: () => {
          console.log('list je vymazany', this.newList.id)
        },
        error: (err) => {
          console.error('chyba pri update listu', err)
        }
      })
    }
  }
}
