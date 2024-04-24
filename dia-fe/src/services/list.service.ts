import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ListModel} from "../models/list.model";

@Injectable({
    providedIn: 'root'
})
export class ListService{

    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'

    getList(id: number, token: string): Observable<any> {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.get<any>(`${this.apiUrl}/todolist/${id}`, { headers });
    }

    postList(listModule: ListModel, token: string):Observable<any>{
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        });

        return this.http.post<any>(`${this.apiUrl}/todolist`, listModule, { headers });
    }

    putList(listModule: ListModel, token: string): Observable<void> {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        });

        return this.http.put<void>(`${this.apiUrl}/todolist/${listModule.id}`, listModule, { headers });
    }
    deleteList(id: number, token: string): Observable<void> {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.delete<void>(`${this.apiUrl}/todolist/${id}`, { headers });
    }


}