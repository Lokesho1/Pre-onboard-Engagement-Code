import { Component, Input, OnInit } from '@angular/core';
import { Doctor } from 'src/app/model/doctor';
import { DoctorService } from 'src/app/service/doctor.service';
import { DataService } from 'src/app/service/dataService';
@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.css']
})
export class AddDoctorComponent implements OnInit {
 
  // data:any;
  constructor(private doctorService:DoctorService, public dataService:DataService) { }

  ngOnInit(): void {
    // this.data=this.dataService.getOption();
    // console.log(this.data);
    // this.editor=this.data.editor;
    
  }

  create(doctor: any) {
    console.log(doctor);
    this.doctorService.addDoctor(doctor).subscribe({
      next: (data: any) => {
        console.log(data);
        
      },
      error: (error: any) => {
        console.log(error);
      }

    });
    location.reload();
  }


}
