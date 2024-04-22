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

  item :{ name: string }[]
  constructor() {
    this.item = [{
      name: 'nieco'
    }]
  }

  @Input()
  name=''

  getItem(){
    //this.itemService.getItem()
  }

  deleteItem(){
    console.log('vymazany item')
    //this.itemService.deleteItem()
  }
  ngOnInit() {
    this.getItem()
  }
}
