import {Component, inject, OnInit} from '@angular/core';
import {NameTimeComponent} from "../../name-time/name-time.component";
import {ListModel} from "../../../models/list.model";
import {CreateNewButtonComponent} from "../../create-new-button/create-new-button.component";
import {ListComponent} from "../../list/list.component";
import {ProgressCircleComponent} from "../../progress-circle/progress-circle.component";
import {NgForOf} from "@angular/common";
import {CalendarComponent} from "../../calendar/calendar.component";
import {ListService} from "../../../services/list.service";
import {ItemCheckedService} from "../../../services/itemChecked.service";
import {ItemService} from "../../../services/item.service";
import {ItemModel} from "../../../models/item.module";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    NameTimeComponent,
    CreateNewButtonComponent,
    ListComponent,
    ProgressCircleComponent,
    NgForOf,
    CalendarComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit{

  constructor(
      private itemCheckService: ItemCheckedService,
      private itemService: ItemService,
      private listService: ListService
  ) {}

  lists: ListModel[] = [];
  listsId: (number | undefined)[] = [];

  ngOnInit(): void {
    this.getAllToDoLists();
  }

  getAllToDoLists() {
    this.listService.getAllLists().subscribe((lists: ListModel[]) => {
      this.lists = lists;
      this.listsId = lists.map(list => list.id);
      this.getAllItemsAndCalculateSuccessRates(); // Voláme funkciu po načítaní zoznamov
    });
  }

  getAllItemsAndCalculateSuccessRates() {
    let requests = this.listsId.map(id => this.itemService.getItemByList(id!));
    forkJoin(requests).subscribe(results => {
      const items: ItemModel[] = results.reduce((acc, val) => acc.concat(val), []);
      this.calculateSuccessRates(items); // Po načítaní všetkých položiek vypočítame úspešnosť
    });
  }

  calculateSuccessRates(items: ItemModel[]) {
    const oneWeekAgo = new Date();
    oneWeekAgo.setDate(oneWeekAgo.getDate() - 7);

    const oneMonthAgo = new Date();
    oneMonthAgo.setMonth(oneMonthAgo.getMonth() - 1);

    let weeklyCheckedCount = 0;
    let monthlyCheckedCount = 0;
    let totalCheckedCount = 0;

    let weeklyTotalCount = 0;
    let monthlyTotalCount = 0;
    let totalCount = items.length;

    // items.forEach(item => {
    //   const isCompletedOnTime = item.completedAt && item.completedAt <= item.deadline;
    //   if (isCompletedOnTime) {
    //     totalCheckedCount++;
    //     if (item.deadline && item.deadline >= oneWeekAgo) {
    //       weeklyCheckedCount++;
    //     }
    //     if (item.deadline && item.deadline >= oneMonthAgo) {
    //       monthlyCheckedCount++;
    //     }
    //   }
    //
    //   if (item.deadline && item.deadline >= oneWeekAgo) {
    //     weeklyTotalCount++;
    //   }
    //   if (item.deadline && item.deadline >= oneMonthAgo) {
    //     monthlyTotalCount++;
    //   }
    // });
    this.lists.forEach(list =>{
      const today = new Date();
      const isCompleted = list.deadline && new Date(list.deadline) <= today;
      if (isCompleted){
        totalCheckedCount++;
        if (list.deadline && list.deadline >= oneWeekAgo){
          weeklyCheckedCount++;
        }
        if (list.deadline && list.deadline >= oneMonthAgo){
          monthlyCheckedCount++;
        }
      }
      if (list.deadline && list.deadline >= oneWeekAgo){
        weeklyTotalCount++;
      }
      if (list.deadline && list.deadline >= oneMonthAgo){
        monthlyTotalCount++;
      }
    })

    this.weeklySuccessRate = Math.round(this.calculatePercentage(weeklyCheckedCount, weeklyTotalCount));
    this.monthlySuccessRate = Math.round(this.calculatePercentage(monthlyCheckedCount, monthlyTotalCount));
    this.overallSuccessRate = Math.round(this.calculatePercentage(totalCheckedCount, totalCount));
  }

  calculatePercentage(checkedItemCount: number, totalItemCount: number): number {
    if (totalItemCount === 0) {
      return 0;
    }
    return (checkedItemCount / totalItemCount) * 100;
  }

  weeklySuccessRate:any = 0;
  monthlySuccessRate: any = 0;
  overallSuccessRate: any = 0;



}
