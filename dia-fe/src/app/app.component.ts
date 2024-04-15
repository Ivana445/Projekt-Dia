import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {AppShellComponent} from "./app-shell/app-shell.component";
import {NavigationComponent} from "./app-shell/navigation/navigation.component";
import {HttpClientModule, provideHttpClient} from "@angular/common/http";

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet, AppShellComponent, NavigationComponent, HttpClientModule],
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent {
    title = 'dia-fe';
}
