import {Component, inject, OnInit} from '@angular/core';
import {ButtonModule} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {ItemComponent} from "../../item/item.component";
import {NgForOf, NgIf} from "@angular/common";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {PaginatorModule} from "primeng/paginator";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "primeng/api";
import {ItemService} from "../../../services/item.service";
import {ListService} from "../../../services/list.service";
import {ListModel} from "../../../models/list.model";
import {ItemModel} from "../../../models/item.module";
import {ShareComponent} from "../../share/share.component";
import {ActivatedRoute, Router} from "@angular/router";
import {ItemCheckedService} from "../../../services/itemChecked.service";

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
        ShareComponent,
        NgIf
    ],
  templateUrl: './list-page.component.html',
  styleUrl: './list-page.component.scss'
})
export class ListPageComponent implements OnInit{

    private readonly listService = inject(ListService);
    private readonly itemService = inject(ItemService);
    private readonly itemChecked = inject(ItemCheckedService);

    //premenne lists
    deadline= new FormGroup({
        date: new FormControl
    });

    newListName: string = '';
    // newList: ListModel = { name: '', deadline: undefined, items: []};
    listId: number = 0;
    list: ListModel = {id: 0,name: '', deadline: undefined, items: []};

    constructor(private route: ActivatedRoute,private router: Router) {
        // this.deadline = this.fb.group({
        //     date: [null]  // Inicializujte date s null alebo hodnotou podľa potreby
        // });

    }

    ngOnInit(): void {
        this.route.paramMap.subscribe(params => {
            const id = params.get('id');
            if (id) {
                this.listId = parseInt(id, 10);
                this.getItems(this.listId);
                this.getListDetails(this.listId);
            }
        });
        //console.log(this.listId, 'id listu')
        console.log( "hodnota deaadline")
    }

    getListDetails(listId: number) {
        this.listService.getList(listId).subscribe({
            next: (listFromDB: ListModel) => {
                this.list = listFromDB;
                this.newListName = this.list.name || '';  // Inicializujte newListName s názvom zoznamu
                if (this.list.deadline) {  // Skontrolujte, či je deadline definovaný
                    this.deadline.patchValue({
                        date: new Date(this.list.deadline)  // Nastavte date s hodnotou deadline
                    });
                }
            },
            error: (error) => {
                console.log('Chyba pri načítavaní detailov zoznamu: ' + error.message);
            }
        });
    }




    //premenne items

    items: ItemModel[] | null = null;
    newItemName = "";
    item: ItemModel | null = null;

    // deleteItemByTrash = new ItemComponent();

    //premenne share
    share:string = '';
    listOfShare = ['user@gmail.com'];

    //GET DATA
    //////////////////////////
    getItems(id: number){
        this.itemService.getItemByList(id).subscribe( (itemFromDB: ItemModel[]) =>{
            console.log("db", itemFromDB)
            this.items = itemFromDB;
        })
    }
    getShare(){
        //todo spracovat odpoved z backendu
    }

    //UPDATE TO DO LIST
    //////////////////////////

    updateList(){
        this.list.name = this.newListName;
        // Získajte hodnotu dátumu z formy
        const deadlineDate = this.deadline.get('date')?.value;
        // Ak je získaný dátum neprázdny a je typu Date, priradíme ho do list.deadline
        if (deadlineDate && deadlineDate instanceof Date) {
            this.list.deadline = deadlineDate;
        }

        this.listService.putList(this.list).subscribe(() => {
            console.log('upravil som list');
            this.refreshList();
        });
    }
    deleteList(){
        if (this.listId != null) {
            this.listService.deleteList(this.listId).subscribe(() => {
                console.log('vymazal som list');
                this.refreshList();
                this.router.navigate(['feature/home-page']);
            })
        }
    }
    refreshList(){
        this.getListDetails(this.listId);
    }
    refreshItems(){
        this.getItems(this.listId);
    }
    addItem(){
        //todo over nullovost
        if (this.item != null){
            this.item.name = this.newItemName;
            this.itemService.postItem(this.listId, this.item).subscribe(() =>{
                console.log('pridal som item');
                this.refreshItems();
            })
        }
    }
    deleteItem(delItem: ItemModel){
        //todo vyskusat ako vymazavat
        // this.itemComponent.deleteItem(delItem);
        this.refreshItems();
        this.refreshList();
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



    ///////////////////////////////////////////////////////
    // checkedItems: Set<number> = new Set();
    // onItemChecked(event: { item: ItemModel, checked: boolean }) {
    //     const itemId = event.item.id;
    //     if (itemId) {
    //         const parsedItemId = parseInt(itemId, 10);
    //         if (!isNaN(parsedItemId)) {
    //             if (event.checked) {
    //                 this.checkedItems.add(parsedItemId);
    //             } else {
    //                 this.checkedItems.delete(parsedItemId);
    //             }
    //         } else {
    //             console.error('Nie je možné skonvertovať id položky na číslo:', itemId);
    //         }
    //     } else {
    //         console.error('Neplatné id položky:', itemId);
    //     }
    // }
    //
    // get checkedItemCount(): number {
    //     return this.checkedItems.size;
    // }

    onItemChecked(event: { item: ItemModel, checked: boolean }) {
        if (event.item.id) {
            const itemId = Number(event.item.id);
            if (event.checked) {
                this.itemChecked.addCheckedItem(itemId);
            } else {
                this.itemChecked.removeCheckedItem(itemId);
            }
        }
    }

    isItemChecked(itemId: string | undefined): boolean {
        if (!itemId) {
            return false;
        }
        const numericItemId = Number(itemId);
        return this.itemChecked.isChecked(numericItemId);
    }

}
