import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ShowListModule} from "../models/show-list.module";

@Injectable({
    providedIn: 'root'
})
export class ListService{

    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'

    getList(listModule: ShowListModule):Observable<any>{
        return this.http.get<any>(`${this.apiUrl}/todolist/${listModule.id}`)
    }

    postList(listModule: ShowListModule):Observable<any>{
        return this.http.post<string>(`${this.apiUrl}/todolist`, {listModule})
    }

    putList(listModule: ShowListModule):Observable<any>{
        return this.http.put<string>(`${this.apiUrl}/todolist/${listModule.id}`, {listModule})
    }
    deleteList(listModule: ShowListModule):Observable<number>{
        return this.http.delete<number>(`${this.apiUrl}/todolist/${listModule.id}`)
    }


}