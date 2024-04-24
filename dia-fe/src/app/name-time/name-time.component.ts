import {Component, inject, OnInit} from '@angular/core';
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
export class NameTimeComponent implements OnInit{

  private readonly userService = inject(UserService)
  private readonly loginService = inject(LoginService)


  user : UserModel | null = this.loginService.getUser()

  username: string | undefined = this.user?.username

  currentTime!: Date;

  constructor() {

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

}