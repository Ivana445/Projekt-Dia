import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-nav-item',
  templateUrl: './nav-item.component.html',
  styleUrl: './nav-item.component.scss',
  standalone: true
})
export class NavItemComponent {
    @Input()
    nazov=''
    @Input()
    icon=''
}