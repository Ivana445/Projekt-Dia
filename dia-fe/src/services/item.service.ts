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


    postItem(list: ListModel, item: ItemModel):Observable<any>{
        return this.http.post<any>(`${this.apiUrl}/todolist/${list.id}/items`, item)
    }


    getItem(item: ItemModel):Observable<any>{
        return this.http.get<any>(`${this.apiUrl}/item/${item.id}`)
    }

    putItem(item: ItemModel):Observable<any>{
        return this.http.put<string>(`${this.apiUrl}/item/${item.id}`, item)
    }

    deleteItem(item: ItemModel):Observable<any>{
        return this.http.delete<any>(`${this.apiUrl}/item/${item.id}`)
    }

}
