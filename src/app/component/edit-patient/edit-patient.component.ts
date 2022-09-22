import { Component, Input, OnInit } from '@angular/core';
import { Patient } from 'src/app/model/patient';
import { DoctorService } from 'src/app/service/doctor.service';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-edit-patient',
  templateUrl: './edit-patient.component.html',
  styleUrls: ['./edit-patient.component.css']
})
export class EditPatientComponent implements OnInit {
  invalid: boolean = false;
  public doctorList!: any;
  public selectList: String[] = [];
  public docChange: boolean = false;

  @Input()
  public editPatient: any = {};
  doct: any;
  constructor(private doctorService: DoctorService, private patientService: PatientService) {
  }
  ngOnInit(): void {
    this.getAllDoctors();
    console.log("insode edit patioent component", this.editPatient);

  }

  getAllDoctors() {
    console.log("inside getAllDoctors");
    this.doctorService.getAllDoctors().subscribe({
      next: (data: any) => {
        console.log(data);
        this.doctorList = data;
        for (let d of this.doctorList) {
          console.log("hi");
          this.selectList.push(d.name + ", specialization: " + d.specialization);
        }
        console.log(this.selectList);

      },
      error: (error: any) => {
        console.log("error in get doctors");
      }
    });
  }

  edit(patient: any) {
    patient.id = this.editPatient.id;
    if (this.docChange) {
      patient.doctor = {
        id: this.doct.id,
        name: this.doct.name,
        specialization: this.doct.specialization,
        phoneNo: this.doct.phoneNo,
        address: this.doct.address
      };
      // this.patientService.addPatient(patient).subscribe();
    }
    else {
      patient.doctor = {
        id: this.editPatient.doctor.id,
        name: this.editPatient.doctor.name,
        specialization: this.editPatient.specialization,
        phoneNo: this.editPatient.phoneNo,
        address: this.editPatient.address      };
      }
      this.doctorService.removePatient(patient.id, this.editPatient.doctor.id).subscribe();
      this.patientService.edit(patient).subscribe();
    
    console.log(patient);
    location.reload();
    // this.patientService.edit(patient).subscribe({
    //   next: (data: any) => {
    //     this.doctorService.assignPatient(patient.id,patient.doctor.id).subscribe({
    //       next: (data: any) => {

    //       }
    //     });
    //   },
    //   error: (error: any) => {
    //     console.log(error);
    //   }

    // });
  }
  changeDoctor(d: any) {
    this.docChange = true;
    console.log(d.target);
    this.doct = JSON.parse(d.target.value)
    // {
    //   id: d.target.value.id,
    //   name: d.target.value.name,
    //   specialized: d.target.value.specialized
    // }
    console.log(this.doct);

  }
}
