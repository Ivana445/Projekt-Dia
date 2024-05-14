import {Component, inject, OnInit} from '@angular/core';
import {ButtonModule} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {ItemComponent} from "../../item/item.component";
import {NgForOf} from "@angular/common";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {PaginatorModule} from "primeng/paginator";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "primeng/api";
import {ItemService} from "../../../services/item.service";
import {ListService} from "../../../services/list.service";
import {LoginService} from "../../../services/client/login.service";
import {ListModel} from "../../../models/list.model";
import {ItemModel} from "../../../models/item.module";

@Component({
  selector: 'app-list-page',
  standalone: true,
    imports: [
        ButtonModule,
        CalendarModule,
        ItemComponent,
        NgForOf,
        OverlayPanelModule,
        PaginatorModule,
        ReactiveFormsModule,
        SharedModule
    ],
  templateUrl: './list-page.component.html',
  styleUrl: './list-page.component.scss'
})
export class ListPageComponent implements OnInit{

    private readonly itemService = inject(ItemService);
    private readonly listService = inject(ListService);
    private readonly loginService = inject(LoginService);



    token = this.loginService.getToken();

    constructor() {
        //console.log('zakliknute itemy',this.deleteItemByTrash.checked)
        //itemComponent.checked = false;
    }
    ngOnInit() {
        //this.addList()
    }

    //premenne lists
    deadline= new FormGroup({
        date: new FormControl
    });
    newListName: string = '';
    newList: ListModel = { name: '', deadline: undefined, items: []};


    //premenne items
    selectedItems: any[] = [];

    //items: ItemModel[] = []
    newItemName: string = '';
    items: ItemModel[] = [
        { name: 'Accounting', popis: 'popis' },
        { name: 'Marketing', popis: 'druhy popis' },
        { name: 'Production', popis: 'treti popis' },
        { name: 'Research', popis: 'stvrty popis' }
    ];

    deleteItemByTrash = new ItemComponent();

    //premenne share
    share:string = '';
    listOfShare = ['user@gmail.com'];


    //CREATE NEW TO DO LIST
    //////////////////////////
    // ked nie je pridany list tak sa itemy zatial ulozia do pola
    addItemToArray(){
        let newItem :ItemModel = {name: this.newItemName};
        this.items.push(<ItemModel>newItem);
        this.newItemName = ''
    }
    //vymazanie z docasneho pola
    deleteItemFromArray(index: number){
        // const allItems = document.querySelectorAll('.app-item');
        // console.log(allItems);
        // allItems.forEach(item => {
        //   item.addEventListener('click', (event) => {
        //     const deleteItem = event.target;
        //     console.log('delete item', deleteItem);
        //     // Tu použiť deleteItem na ďalšie manipulácie s daným prvkom
        //   });
        // });
        // if (this.deleteItemByTrash.click){
        //   console.log(this.deleteItemByTrash.click, 'ci je to dobre')
        //   this.items.splice(index, 1);
        // }

    }

    /*addItemToDatabase(newList: ListModel){
      if (this.items != null) { // Kontrola, ci nová položka nie je prázdna
        if (this.token != null) { //Kontrola ci je uzivatel prihlaseny
          for (let i = 0; i < this.items.length; i++){ // prejst cely zoznam a pridat kazdu polozku ako item
            this.itemService.postItem(newList, this.items[i], this.token).subscribe(() => {
              console.log('pridal som item s indexom', i);
              //po pridani do databazy sa vymaze pole
              this.items = []
            })
          }
        }
      }
    }*/

    addList() {
        if (this.newListName.trim() && this.deadline.controls.date.value) {
            // Predpokladám, že token je
            this.newList.name = this.newListName;
            this.newList.deadline = this.deadline.controls.date.value
            this.newList.items = this.items//spojenie do modelu
            this.newList.share = this.listOfShare
            console.log(this.newList)
            this.listService.postList(this.newList).subscribe({
                    next: () => {
                        console.log('pridal som list');
                        this.newListName = '';
                        this.items = [];
                        this.listOfShare = [];
                        //spravit prepojenie na list, ktory sme vytvorili
                    },
                    error: (err) =>{
                        console.error('chyba pri pridani listu', err);
                    }
                }

            );
        }
    }


    //ZDIELANIE LISTU
    //////////////////////////

    shareList(email: string){
        console.log('zdielam', email );
        this.listOfShare.push(this.share);
        this.share = ''
    }

    // addShareListToDatabase(){
    //   for (let i = 0; i < this.listOfShare.length; i++){
    //     const user = this.loginService.getUser();
    //     if (user && this.token)
    //       this.listService.addUser(user, this.listOfShare[i])
    //
    //   }
    //
    // }
    deleteShare(index: number){
        this.listOfShare.splice(index, 1);
    }

}
