import { HttpInterceptorFn } from '@angular/common/http';
import {LoginService} from "../../services/client/login.service";
import {inject} from "@angular/core";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const loginService = inject(LoginService);
  const token =loginService.getToken();
  if (token != null){
    req = req.clone({
      headers: req.headers.append('Authorization', token)
    })
  }
  return next(req);
};
