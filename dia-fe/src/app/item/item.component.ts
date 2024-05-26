import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {ItemService} from "../../services/item.service";
import {ItemModel} from "../../models/item.module";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-item',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './item.component.html',
  styleUrl: './item.component.scss'
})
export class ItemComponent implements OnInit{

  private readonly itemService = inject(ItemService)

  @Input() item: ItemModel | null = null; // Vstupná premenná pre položku
  @Input() isChecked: boolean = false; // Premenná pre uchovanie stavu začiarknutia

  @Output() itemChecked = new EventEmitter<{ item: ItemModel, checked: boolean }>(); // Výstupná udalosť pre označenie položky


  // Metóda pre označenie položky
  onCheckboxChange() {
    this.isChecked = !this.isChecked;
    if (this.item) {
      this.itemChecked.emit({ item: this.item, checked: this.isChecked });
    }
  }
  click = false;

  constructor() {
  }


  deleteItem(delItem: ItemModel){
    this.itemService.deleteItem(delItem).subscribe(() => {
          console.log('vymazal som item');
          // this.refreshItems();
          // this.refreshList();
      })
  }
  ngOnInit() {
  }
}
