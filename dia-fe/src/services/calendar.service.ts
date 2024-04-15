import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CalendarModel} from "../models/calendar.model";

@Injectable({
    providedIn: 'root'
})
export class CalendarService {
    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'

    postCalendar(calendarModel: CalendarModel): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/calendar`, {calendarModel})
    }

    getCalendar(calendarModel: CalendarModel): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/calendar/${calendarModel.id}`)
    }
    //nedorobene put v calendar
    putCalendar(calendarModel: CalendarModel): Observable<any> {
        return this.http.put<any>(`${this.apiUrl}/calendar/${calendarModel.id}`, {calendarModel})
    }
    deleteCalendar(calendarModel: CalendarModel): Observable<any> {
        return this.http.delete<any>(`${this.apiUrl}/calendar/${calendarModel.id}`)
    }
}