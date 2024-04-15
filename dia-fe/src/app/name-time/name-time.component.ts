import {Component, inject} from '@angular/core';
import {DatePipe} from "@angular/common";
import {UserService} from "../../services/client/user.service";

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


  username='Blueberry'
  //time='10:55'

  currentTime!: Date;

  constructor() { }


  ngOnInit(): void {
    this.updateTime();
    setInterval(() => {
      this.updateTime();
    }, 1000); // Update time every second
  }

  updateTime(): void {
    this.currentTime = new Date();
  }

  userName():string{
    //this.userService.getUsername()
    return 'null'
  }
}
