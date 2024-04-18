import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Observable, switchMap, tap} from "rxjs";
import {UserModel} from "../../models/user.model";


@Injectable({
    providedIn: 'root'
})
export class LoginService{

    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'

    private apiUrlAuth: string = "http://localhost:8080/api/login";

    constructor() {
    }


    login(username: string, password:string): Observable<any>{
        return this.http.post<any>(`${this.apiUrl}/login`, { username, password });
    }
    register(username: string, email: string, password:string): Observable<any>{
        return this.http.post<any>(`${this.apiUrl}/registration`, { username, email, password });
    }

    newlogin(username: string | undefined, password: string | undefined): Observable<any>{

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa(username +':'+ password)
        });
        return this.http.post<any>(this.apiUrlAuth, null, {headers, observe: 'response'}).pipe(
            tap((response: HttpResponse<any>) => this.setToken(response.headers.get("Authorization"))),
            switchMap((user: HttpResponse<any>) => this.http.get<any>(this.apiUrlAuth).pipe(
                    tap((user: UserModel) => this.setUser(user))
                )
            )
        )
    }

    setToken(token: string|null) {
        if (token != null)
            localStorage.setItem('token', token);
        else
            localStorage.removeItem('token');
    }

    getToken(): string | null {
        return localStorage.getItem('token');
    }

    setUser(user: UserModel) {
        localStorage.setItem('user', JSON.stringify(user));
    }

    getUser(): UserModel|null {
        const s = localStorage.getItem('user');
        if (s != null)
            return JSON.parse(s);
        return null;
    }

    isLogged(): boolean {
        return this.getToken() !== null;
    }


    logout(): Observable<any> {
        return this.http.delete(this.apiUrlAuth, {}).pipe(
            tap( () => { localStorage.removeItem('token'); localStorage.removeItem('user'); } )
        )
    }
}
