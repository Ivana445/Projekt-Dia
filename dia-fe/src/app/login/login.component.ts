import {Component, inject, Output} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
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
        HttpClientModule,
        ReactiveFormsModule
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent {
    private readonly loginService = inject(LoginService)
    private readonly router = inject(Router)


    constructor(private formBuilder: FormBuilder) {
    }

    register = false

    login: FormGroup = this.formBuilder.group({
        username: [''],
        email: [''],
        password: ['']
    })



    newloginSubmit() {
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

    loginBackSubmit() {
        this.register = false
    }

    registerPageSubmit() {
        this.register = true
    }

    registerSubmit() {
        if (this.login.value.name != null && this.login.value.email != null && this.login.value.password != null) {
            this.loginService.register(this.login.value.name, this.login.value.email, this.login.value.password).subscribe(
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
        const username = this.login.value.username
        const password = this.login.value.password
        if (this.login.value.username != null && this.login.value.password != null) {
                this.loginService.newlogin(username, password).subscribe({
                    next: ()=> {
                        console.log('prihlaseny')
                    },
                    error: (e) => {
                        console.log('chyba prihlasenia')
                    }
                })
        }
    }

}
