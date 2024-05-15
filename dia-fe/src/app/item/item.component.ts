import {Component, inject, Input, OnInit} from '@angular/core';
import {ItemService} from "../../services/item.service";
import {ItemModel} from "../../models/item.module";

@Component({
  selector: 'app-item',
  standalone: true,
  imports: [],
  templateUrl: './item.component.html',
  styleUrl: './item.component.scss'
})
export class ItemComponent implements OnInit{

  private readonly itemService = inject(ItemService)

  @Input()
  itemname: string = "";

  click = false;

  constructor() {
  }

  getItem(){
    //this.itemService.getItem()
  }

  deleteItem(){
    console.log('vymazany item');
    this.click = true;
    console.log(this.click)
    //this.itemService.deleteItem()
  }
  ngOnInit() {
    this.getItem()
  }
}
