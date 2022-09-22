import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DataService } from 'src/app/service/dataService';
import { PatientService } from 'src/app/service/patient.service';
import { Location } from '@angular/common';
import { CheckupSercviceService } from 'src/app/service/checkup-sercvice.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  
  public patient:any;
  public checkups:any;
  
  public checkup:boolean=false;
  public addcheck:boolean=false;

  constructor(private checkupService:CheckupSercviceService ,private location:Location, private route:ActivatedRoute,private router:Router, private patientService:PatientService, private dataService:DataService) { 
    
    // console.log("val 1",history.state.pat);
    // this.patient=JSON.parse(history.state.pat);
  }
  
  
  // =this.login.details;
  // ={"id":1,"name":"Dr. Sathi","specialized":"General","patients":[{"id":10,"name":"HAri","admissionDate":"1111-11-11"},{"id":5,"name":"hjkl","admissionDate":"5555-05-05"},{"id":7,"name":"juii","admissionDate":"4444-04-04"},{"id":1,"name":"Hari","admissionDate":"3333-03-31"}]};
  ngOnInit(): void {
    this.patientService.get(this.dataService.getOption().patientId).subscribe({
          next:(data:any)=>{
            this.patient=JSON.parse(data);
            console.log(this.patient);
            
          },
          error:(error:any)=>{
            console.log("error p",error);
            
          }
        });      
    this.checkupService.getpatientById(this.dataService.getOption().patientId).subscribe({
          next:(data:any)=>{
            let temp=data;
            console.log(data);
            
            temp.forEach((element:any) => {
              let [y,m,d]=element.checkUpDate.substring(0,10).split("-");
              element.checkUpDate=d+"-"+m+"-"+y;
            });
            this.checkups=temp;
            console.log("checkup1",this.checkups);
            
          },
          error:(error:any)=>{
            console.log("error c",error);
            
          }
        });      
    console.log("patinet",this.patient);
    console.log("checkups2",this.checkups);
    
    this.checkup=this.dataService.getOption().checkup;

    // console.log(this.patient);
    // this.patient=this.router.snapshot.paramMap.get('id');
    // // this.patientService.get(this.dataService.getOption().patientId).subscribe({
      
      
    //   this.patientService.get(this.ids).subscribe({
    //     next:(data:any)=>{
    //       this.patient=JSON.parse(data);
    //       console.log(this.patient);
          
    //     }
    //   });

    // console.log("val",this.location.getState());
    }


    addcheckup(){
      this.addcheck=true;
    }
}
