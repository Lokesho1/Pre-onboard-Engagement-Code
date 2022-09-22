import { Component, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { pipe } from 'rxjs';
import { Doctor } from 'src/app/model/doctor';
import { Patient } from 'src/app/model/patient';
import { DoctorService } from 'src/app/service/doctor.service';
import { PatientService } from 'src/app/service/patient.service';
@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']

})
export class AddPatientComponent implements OnInit {

  invalid: boolean = false;

  public doctorList!: any;
  public selectList: String[] = [];

  constructor(private doctorService: DoctorService, private patientService: PatientService, private router:Router) { }

  ngOnInit(): void {
    this.getAllDoctors();

  }

  getAllDoctors() {
    console.log("inside getAllDoctors");
    this.doctorService.getAllDoctors().subscribe({
      next: (data: any) => {
        console.log(data);
        this.doctorList = data;
        for (let d of this.doctorList) {
          console.log("hi0",d);
          this.selectList.push(d.name + ", specialization: " + d.specialization);
        }
        console.log(this.selectList);

      },
      error: (error: any) => {
        console.log("error in get doctors");
      }
    });
  }

  p: any;
  create(patient: any) {
    patient.doctor = JSON.parse(patient.doctor);
    console.log(patient);
    this.p={
      name : patient.name,
      admissionDate : patient.admissionDate,
      phoneNo:patient.phoneNo,
      address:patient.address,
      doctor:
      {
        id : patient.doctor.id,
        name : patient.doctor.name,
        specialization : patient.doctor.specialization,
        phoneNo:patient.doctor.phoneNo,
        address:patient.doctor.address
      }
    }

    // console.log(this.p);

    this.patientService.addPatient(this.p).subscribe();
    location.reload();
    // this.router.navigate(['admin']);

  }
}
