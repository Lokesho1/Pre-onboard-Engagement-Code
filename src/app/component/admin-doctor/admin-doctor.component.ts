import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Doctor } from 'src/app/model/doctor';
import { DoctorService } from 'src/app/service/doctor.service';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/dataService';
import { ViewportScroller } from '@angular/common';

@Component({
  selector: 'app-admin-doctor',
  templateUrl: './admin-doctor.component.html',
  styleUrls: ['./admin-doctor.component.css']
})
export class AdminDoctorComponent implements OnInit {
  display:boolean=false;
  editor:boolean=false;
  editDoctor!:any
  doctorList:any=[];

  constructor(private doctorService:DoctorService, private router:Router, private dataService:DataService,private viewportScroller:ViewportScroller) { }

  ngOnInit(): void {
    this.getAllDoctors();
    console.log(this.doctorList);
  }
  

  getAllDoctors(){
    console.log("inside getAllPatients");
    this.doctorService.getAllDoctors().subscribe(
      (data: any) => {
        console.log(data);
        this.doctorList=data;
      }
    );
  }

  create(){
    console.log("inside create doctor ");
    this.display=true;
    this.editor=false;
    // this.dataService.setOption("editor",false);
    // this.dataService.setOption("doctor",{});
  }
  
  view(doctor:any){
    console.log("inside view doctor ",doctor);
    this.router.navigate(['doctor']);
    this.dataService.setOption("doctorId",doctor.id);

  }
  
  edit(doctor:any){
    this.display=true;
    this.editor=true;
    this.editDoctor=doctor;
    console.log("inside edit Doctor ",doctor);
    // this.dataService.setOption("editor",true);
    // this.dataService.setOption("doctor",doctor);

  }
  delete(id:number){
    console.log("inside delete patient ",id);
    this.doctorService.delete(id).subscribe(
      (data: any) => {
        console.log(data);
        this.getAllDoctors();
      }
    );
  }
}
