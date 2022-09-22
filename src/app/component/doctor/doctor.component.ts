import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/dataService';
import { DoctorService } from 'src/app/service/doctor.service';
@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {


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

}
