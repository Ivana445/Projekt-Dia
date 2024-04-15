import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable({
    providedIn: 'root'
})
export class LoginService{

    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'

    constructor() {
    }


    login(username: string, password:string): Observable<any>{
        return this.http.post<any>(`${this.apiUrl}/login`, { username, password });
    }
    register(username: string, email: string, password:string): Observable<any>{
        return this.http.post<any>(`${this.apiUrl}/registration`, { username, email, password });
    }

}