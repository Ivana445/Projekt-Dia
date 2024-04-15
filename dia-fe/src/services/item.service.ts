import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ItemModel} from "../models/item.module";

@Injectable({
    providedIn: 'root'
})
export class ItemService{

    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'


//items nie je dokoncene
    postItem(item: ItemModel):Observable<any>{
        return this.http.post<any>(`${this.apiUrl}/`, {item})
    }


    getItem(item: ItemModel):Observable<any>{
        return this.http.get<any>(`${this.apiUrl}/${item.id}`)
    }


    deleteItem(item: ItemModel):Observable<any>{
        return this.http.delete<any>(`${this.apiUrl}/${item.id}`)
    }

}