import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../../models/user.model";


@Injectable({
    providedIn: 'root'
})
export class UserService{

    http = inject(HttpClient)
    apiUrl = 'http://localhost:8080/api'

    constructor() {
    }

    getUsername(user: UserModel){
        return this.http.get<string>(`${this.apiUrl}/login${user.id}`)
        //const token = localStorage.getItem('authToken')
    }
    getPassword(user: UserModel){
        return this.http.get<string>(`${this.apiUrl}/login/${user.id}`)
    }
    changePassword(user: UserModel){
        return this.http.put(`${this.apiUrl}/change/${user.id}`, {user})
    }

    getRegistration(user: UserModel){
        return this.http.get<any>(`${this.apiUrl}/registration${user.id}`)
    }

}