import {Component, inject} from '@angular/core';
import {DatePipe} from "@angular/common";
import {UserService} from "../../services/client/user.service";
import {UserModel} from "../../models/user.model";
import {LoginService} from "../../services/client/login.service";

@Component({
  selector: 'app-name-time',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './name-time.component.html',
  styleUrl: './name-time.component.scss'
})
export class NameTimeComponent {

  private readonly userService = inject(UserService)
  private readonly loginService = inject(LoginService)


  user : UserModel | null

  username: string | undefined =''
  //time='10:55'

  currentTime!: Date;

  constructor() {
    this.user = this.loginService.getUser()
  }


  ngOnInit(): void {
    this.updateTime();
    setInterval(() => {
      this.updateTime();
    }, 1000); // Update time every second
  }

  updateTime(): void {
    this.currentTime = new Date();
  }

  userName(){
    this.username = this.user?.username
  }
}