import {Component, inject, Input, OnInit} from '@angular/core';
import {UserService} from "../../services/client/user.service";
import {LoginService} from "../../services/client/login.service";
import {ListService} from "../../services/list.service";
import {data} from "autoprefixer";
import {ListModel} from "../../models/list.model";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent implements OnInit{

  private readonly loginService = inject(LoginService)
  private readonly listService = inject(ListService)

  user = this.loginService.getUser()

  @Input()
  name=''


  ngOnInit():void{
  }

}
