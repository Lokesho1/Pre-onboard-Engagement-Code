import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {


  role!:String;
  url: String = "http://localhost:8090/auth/";

  constructor(private _httpClient: HttpClient, private router: Router) { }

  authenticate(user: any): any {
    return this._httpClient.post(this.url + "authenticate", user);
  }

  register(user: any): any {
    return this._httpClient.post(this.url + "register", user, { responseType: 'text' });
  }

  getToken() {
    return localStorage.getItem('token') || '';
  }
  getRole() {
    return localStorage.getItem('role') || '';
  }
  isLoggedIn(){
    return localStorage.getItem('token')!=null;
  }

  logout(){
    localStorage.clear();
    this.router.navigateByUrl('/login');
  }
}
