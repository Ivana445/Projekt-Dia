import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ListModel} from "../models/list.model";
import {UserModel} from "../models/user.model";

@Injectable({
    providedIn: 'root'
})
export class ListService{

    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'

    getList(id: number): Observable<ListModel> {
        return this.http.get<ListModel>(`${this.apiUrl}/todolist/${id}`);
    }

    postList(listModule: ListModel):Observable<any>{
        return this.http.post<any>(`${this.apiUrl}/todolist`, listModule);
    }

    putList(listModule: ListModel): Observable<void> {
        return this.http.put<void>(`${this.apiUrl}/todolist/${listModule.id}`, listModule);
    }
    addUser(currentUser :UserModel,addedUser: string):Observable<any>{
        return this.http.put<any>(`${this.apiUrl}/todolist/change/${currentUser.id}`, addedUser)
    }
    deleteList(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/todolist/${id}`);
    }
    getAllLists():Observable<ListModel[]>{
        return this.http.get<ListModel[]>(`${this.apiUrl}/todolist/all`)
    }


}