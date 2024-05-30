import {Component, inject} from '@angular/core';
import {ProgressCircleComponent} from "../../progress-circle/progress-circle.component";
import {NameTimeComponent} from "../../name-time/name-time.component";
import {UserModel} from "../../../models/user.model";
import {LoginService} from "../../../services/client/login.service";
import {UserService} from "../../../services/client/user.service";

@Component({
  selector: 'app-profile-page',
  standalone: true,
  imports: [
    ProgressCircleComponent,
    NameTimeComponent
  ],
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.scss'
})
export class ProfilePageComponent {

  private readonly loginService = inject(LoginService)
  private readonly userService = inject(UserService)

  user: UserModel | null = {username: "", password: "", email: ""};
  password: string = '';

  constructor() {
    this.user = this.loginService.getUser()
    console.log(this.user)
  }

  udpatePassword(){
    if (this.user) {
      this.user.password = this.password;
      this.userService.changePassword(this.user).subscribe(() => {
        console.log('heslo zmenene')
      })
    }
  }



}
