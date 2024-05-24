import {Component, Input} from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
    selector: 'app-nav-item',
    templateUrl: './nav-item.component.html',
    styleUrl: './nav-item.component.scss',
    imports: [
        RouterLink
    ],
    standalone: true
})
export class NavItemComponent {
    @Input()
    routerLink!: string;
    @Input()
    nazov!: string;
    @Input()
    icon=''
}
