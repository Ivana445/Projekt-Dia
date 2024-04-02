import { Component } from '@angular/core';
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

  constructor(private userService: UserService) {
  }
  onSubmit(username: string, password: string) {
    this.userService.login(username, password).subscribe(
        (response) => {
          // Handle successful login response here
          console.log('Login successful', response);
        },
        (error) => {
          // Handle login error here
          console.error('Login error', error);
        }
    );
  }
}
