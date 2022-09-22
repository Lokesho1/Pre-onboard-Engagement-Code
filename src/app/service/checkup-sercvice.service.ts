import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CheckupSercviceService {

  url: String = "http://localhost:8090/checkup/";

  constructor(private _httpClient:HttpClient,private router:Router) { }

  getpatientById(pid:number){
    return this._httpClient.get(this.url + "getbypatient"+"/"+pid);
  }
  addcheckup(checkup:any){
    return this._httpClient.post(this.url + "add",checkup);
  }
}
