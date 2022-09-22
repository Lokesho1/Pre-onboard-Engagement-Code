import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthorizationService } from './authorization.service';
@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{
  constructor(private inject:Injector) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let service = this.inject.get(AuthorizationService);
    let token = req.clone({
      setHeaders: {
        Authorization: 'Bearer '+service.getToken()
      }
    });
    return next.handle(token);
  }

}
