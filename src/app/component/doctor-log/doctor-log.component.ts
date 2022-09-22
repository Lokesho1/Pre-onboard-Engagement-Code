import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/dataService';
import { DoctorService } from 'src/app/service/doctor.service';

@Component({
  selector: 'app-doctor-log',
  templateUrl: './doctor-log.component.html',
  styleUrls: ['./doctor-log.component.css']
})
export class DoctorLogComponent implements OnInit {

  constructor(private router:Router,private doctorService:DoctorService, private dataService:DataService) { }

  public doctor:any;
  ngOnInit(): void {
    this.doctorService.get(this.dataService.getOption().doctorId).subscribe({
      next:(data:any)=>{
        this.doctor=JSON.parse(data);
        console.log(this.doctor);
        
      }
    });
  }

  addcheckup(patient:any){
    this.dataService.setOption("checkup",true);
    this.dataService.setOption("patientId",patient.id);
    this.router.navigate(['patient']);
  }
  
}
