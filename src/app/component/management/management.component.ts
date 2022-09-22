import { Component, OnInit, Input, Output } from '@angular/core';
import { PatientService } from 'src/app/service/patient.service';
import { Patient } from 'src/app/model/patient';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/dataService';
import { DoctorService } from 'src/app/service/doctor.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css']
})
export class ManagementComponent implements OnInit {

  name=new FormControl('');
  id=new FormControl('');
  
  display:boolean=false;
  editor:boolean=false;
  editPatient:any={};
  patientList: Array<any> = ([]);

  constructor(private patientService: PatientService,private doctorService:DoctorService, private router: Router, private dataService:DataService) { }

  ngOnInit(): void {
    this.getAllPatients();
    console.log(this.patientList);
  }

  getAllPatients() {
    console.log("inside getAllPatients");
    this.patientService.getAllPatient().subscribe(
      (data: any) => {
        console.log(data);
        this.patientList = data;
      }
    );
  }

  create() {
    console.log("inside create patient ");
    this.display=true;
    this.editor=false;
    // this.router.navigate([{ outlets: { inner: ['addPatient'] } }]);
    
  }
  
  edit(patient: Patient) {
    this.display=true;
    this.editor=true;
    this.editPatient=patient;
    console.log("inside edit patient ", this.editPatient);
  }
  
  delete(patient: any) {
    console.log("inside delete patient ", patient);
    this.patientService.delete(patient.id).subscribe(
      (data: any) => {
        console.log(data);
        this.getAllPatients();
      }
      );
      // this.doctorService.removePatient(patient.id, patient.doctor.id).subscribe();
      // location.reload();
    }
    
    view(patient: any) {
      console.log("inside view patient ", patient);
      this.dataService.setOption("patientId",patient.id);
      this.router.navigate(['patient']);
    
  }

  searchById(){
    console.log(this.id);
    
  }

  searchByName(){
    console.log(this.name);
    
  }
}

