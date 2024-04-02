import {Component, Input, OnInit} from '@angular/core';
import {DatePipe, formatDate} from "@angular/common";

@Component({
  selector: 'app-name-time',
  templateUrl: './name-time.component.html',
  styleUrl: './name-time.component.scss'
})
export class NameTimeComponent implements OnInit{

  username='Blueberry'
  time='10:55'

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
  }}
