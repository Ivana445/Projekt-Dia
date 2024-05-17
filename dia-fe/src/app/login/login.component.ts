import {Component, inject} from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {DividerModule} from "primeng/divider";
import {PasswordModule} from "primeng/password";
import {ButtonModule} from "primeng/button";
import {Router, RouterLink} from "@angular/router";
import {NgIf, NgOptimizedImage} from "@angular/common";
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
        HttpClientModule,
        ReactiveFormsModule,
        NgOptimizedImage
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent {
    private readonly loginService = inject(LoginService)
    private readonly router = inject(Router)


    constructor() {
    }

    register = false

    login= new FormGroup ({
        username: new FormControl('', Validators.required),
        email: new FormControl('', [Validators.required, Validators.email]),
        password: new FormControl('', Validators.required)
    })



    /*newloginSubmit() {
        if (this.login.value.name != null && this.login.value.password != null) {
            this.loginService.login(this.login.value.name, this.login.value.password).subscribe(
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

     */

    loginBackSubmit() {
        this.register = false
    }

    registerPageSubmit() {
        this.register = true
    }

    registerSubmit() {
        if (this.login.controls.username.value != null && this.login.controls.email.value != null && this.login.controls.password.value != null) {
            this.loginService.register(this.login.controls.username.value, this.login.controls.email.value, this.login.controls.password.value).subscribe(
                (response) => {
                    this.router.navigate(['feature/home-page'])
                        .then(() => console.log('uspesne prihlaseny', response))
                        .catch(error => {
                            console.error('chybne prihlasenie', error)
                        })
                });
        }
    }

    loginSubmit(){
        if (this.login.controls.username.value && this.login.controls.password.value) {
                this.loginService.newlogin(this.login.controls.username.value, this.login.controls.password.value).subscribe({
                    next: ()=> {
                        this.router.navigate(['feature/home-page']).then()
                        console.log('prihlaseny')
                    },
                    error: (err) => {
                        console.log('chyba prihlasenia', err)
                    }
                })
        }
    }

}
