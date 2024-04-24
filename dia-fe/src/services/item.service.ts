import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ItemModel} from "../models/item.module";
import {ListModel} from "../models/list.model";

@Injectable({
    providedIn: 'root'
})
export class ItemService{

    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'


    postItem(list: ListModel, item: ItemModel, token: string):Observable<any>{
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        });
        return this.http.post<any>(`${this.apiUrl}/todolist/${list.id}/items`, item,{headers})
    }


    getItem(item: ItemModel, token: string):Observable<any>{
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });
        return this.http.get<any>(`${this.apiUrl}/item/${item.id}`, {headers})
    }

    putItem(item: ItemModel, token : string):Observable<any>{
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        });
        return this.http.put<string>(`${this.apiUrl}/item/${item.id}`, item, {headers})
    }

    deleteItem(item: ItemModel, token: string):Observable<any>{
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });
        return this.http.delete<any>(`${this.apiUrl}/item/${item.id}`, {headers})
    }

}
