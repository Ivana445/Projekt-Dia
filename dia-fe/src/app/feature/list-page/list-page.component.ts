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
import {ShareComponent} from "../../share/share.component";
import {error} from "@angular/compiler-cli/src/transformers/util";

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
        SharedModule,
        ShareComponent
    ],
  templateUrl: './list-page.component.html',
  styleUrl: './list-page.component.scss'
})
export class ListPageComponent implements OnInit{

    private readonly listService = inject(ListService);
    private readonly loginService = inject(LoginService);
    private readonly itemService = inject(ItemService);

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


    newItemName: string = '';
    item: ItemModel = {name: "", popis: ""};
    items: ItemModel[] = [
        // { name: 'Accounting', popis: 'popis' },
        // { name: 'Marketing', popis: 'druhy popis' },
        // { name: 'Production', popis: 'treti popis' },
        // { name: 'Research', popis: 'stvrty popis' }
    ];

    deleteItemByTrash = new ItemComponent();

    //premenne share
    share:string = '';
    listOfShare = ['user@gmail.com'];

    //GET DATA
    //////////////////////////
    getItems(){
        //todo ako vybrat item z databazy?, pravdepodobne to pojde len pomocou id listu - prerobit backend

        // this.itemService.getItem(/*neviem poslat item ktory chcem ziskat*/this.newList).subscribe( (itemFromDB: ItemModel[]) =>{
        //     this.items = itemFromDB;
        // })
    }
    getShare(){
        //todo spracovat odpoved z backendu
    }

    //UPDATE TO DO LIST
    //////////////////////////

    updateList(){
        this.newList.name = this.newListName;
        this.newList.deadline = this.deadline.controls.date.value;
        this.listService.putList(this.newList).subscribe(() => {
            console.log('upravil som list');
        })
    }
    deleteList(){
        if (this.newList.id != null) {
            this.listService.deleteList(this.newList.id).subscribe(() => {
                console.log('vymazal som list');
            })
        }
    }

    addItem(){
        this.item.name = this.newItemName;
        this.itemService.postItem(this.newList, this.item).subscribe(() =>{
            console.log('pridal som item');
        })
    }
    deleteItem(){
        this.itemService.deleteItem(this.item).subscribe(() => {
            console.log('vymazal som item');
        })
    }

    //ZDIELANIE LISTU
    //////////////////////////

    shareList(email: string){
        console.log('zdielam', email );
        this.listOfShare.push(this.share);
        this.share = '';
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
