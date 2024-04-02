import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  http = inject(HttpClient)
  apiUrl = 'http://localhost:8080/api'

  login(username: string, password:string): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}/login`, { username, password });
  }

  registration(username: string, password: string, email: string): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}/registration`, { username, password, email });

  }
  logOut(token: string): Observable<string>{
    // return this.http.get<string>(`${this.apiUrl}/logout`,{
    //   params:{
    //     token: token
    //   }
    // })
    return of('Success')
  }
}
