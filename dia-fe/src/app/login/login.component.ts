import {Component, inject} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {DividerModule} from "primeng/divider";
import {PasswordModule} from "primeng/password";
import {ButtonModule} from "primeng/button";
import {Router, RouterLink} from "@angular/router";
import {NgIf} from "@angular/common";
import {LoginService} from "../../services/client/login.service";
import {HttpClientModule} from "@angular/common/http";


@Component({
    selector: 'app-login',
    standalone: true,
    imports: [
        FormsModule,
        InputTextModule,
        DividerModule,
        PasswordModule,
        ButtonModule,
        RouterLink,
        NgIf,
        HttpClientModule
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent {
    private readonly loginService = inject(LoginService)
    private readonly router = inject(Router)

    value_username: string | undefined;
    value_password: string | undefined;
    value_email: string | undefined;
    register = false


    loginSubmit() {
        if (this.value_username != null && this.value_password != null) {
            this.loginService.login(this.value_username, this.value_password).subscribe(
                (response) => {
                    this.router.navigate(['feature/home-page'])
                        .then(() => console.log('uspesne prihlaseny', response))
                        .catch(error => {
                            console.error('chybne prihlasenie', error)
                        })
                }
            );
        }
    }

    loginBackSubmit() {
        this.register = false
    }

    registerPageSubmit() {
        this.register = true
    }

    registerSubmit() {
        if (this.value_username != null && this.value_email != null && this.value_password != null) {
            this.loginService.register(this.value_username, this.value_email, this.value_password).subscribe(
                (response) => {
                    this.router.navigate(['feature/home-page'])
                        .then(() => console.log('uspesne prihlaseny', response))
                        .catch(error => {
                            console.error('chybne prihlasenie', error)
                        })
                });
        }
    }

}
