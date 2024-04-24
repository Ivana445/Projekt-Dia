import { HttpInterceptorFn } from '@angular/common/http';
import {LoginService} from "../../services/client/login.service";
import {inject} from "@angular/core";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const loginService = inject(LoginService);
  const token =loginService.getToken();
  console.log('aaaaaaaaaaaaaaaa:' + req.url)
  if (!req.url.endsWith("/api/login") && token != null){
    console.log('posielam token:' + token)
    req = req.clone({
      headers: req.headers.append('Authorization', token)
    })
  }
  return next(req);
};
