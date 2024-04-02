import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-show-list',
  templateUrl: './show-list.component.html',
  styleUrl: './show-list.component.scss'
})
export class ShowListComponent {

  @Input()
  TODOlistname=''
}
