import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../model/patient';
@Injectable({
  providedIn: 'root'
})
export class PatientService {

  url: String = "http://localhost:8090/patient/";

  constructor(private _httpClient: HttpClient, private router: Router) { }

  getAllPatient(){
    return this._httpClient.get(this.url + "getPatients");

  }
  
  delete(id:number){
    return this._httpClient.get(this.url + "delete/"+id, {responseType:'text'});

  }
  
  get(id:number){
    return this._httpClient.get(this.url + "getPatient/"+id, {responseType:'text'});

  }

  addPatient(patient:any){
    return this._httpClient.post(this.url + "create", patient);
  }
  
  edit(patient:any){
    return this._httpClient.post(this.url + "update", patient);

  }

}
