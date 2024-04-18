import {Component, inject, Input, OnInit} from '@angular/core';
import {ItemService} from "../../services/item.service";

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
  name=''

  getItem(){
    //this.itemService.getItem()
  }

  ngOnInit() {
    this.getItem()
  }
}
