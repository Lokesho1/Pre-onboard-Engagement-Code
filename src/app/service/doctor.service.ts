import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Doctor } from '../model/doctor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  id!:number;
  url: String = "http://localhost:8090/doctor/";

  constructor(private _httpClient: HttpClient, private router: Router) { }

  getAllDoctors(){
    return this._httpClient.get(this.url + "getAllDoctors");
  }

  delete(id:number){
    return this._httpClient.get(this.url + "delete/"+id, {responseType:'text'});
    
  }
  addDoctor(doctor:Doctor){
    return this._httpClient.post(this.url + "create", doctor);
  }

  update(doctor:Doctor){
    return this._httpClient.post(this.url + "update", doctor);
  }
  
  
  get(id:number){
    return this._httpClient.get(this.url + "get/"+id, {responseType:'text'});

  }

  assignPatient(patientId:number,doctorId:number){
    return this._httpClient.get(this.url +""+doctorId+"/assignPatient/"+patientId, {responseType:'text'});

  }
 
  removePatient(patientId:number,doctorId:number){
    return this._httpClient.get(this.url +"" +doctorId+"/removePatient/"+patientId, {responseType:'text'});
  }

  setId(did:number){
    this.id=did;
  }
  getId():number{
    return this.id;
  }
}
