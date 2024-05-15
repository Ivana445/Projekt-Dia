import {Component, inject, Input} from '@angular/core';
import {ItemService} from "../../services/item.service";
import {ShareModel} from "../../models/share.model";

@Component({
  selector: 'app-share',
  standalone: true,
  imports: [],
  templateUrl: './share.component.html',
  styleUrl: './share.component.scss'
})
export class ShareComponent {
  private readonly itemService = inject(ItemService)


  @Input()
  shareEmail: string ="";
  constructor() {
  }
  deleteShare(){
    console.log('vymazem zdielanie');
  }

}
