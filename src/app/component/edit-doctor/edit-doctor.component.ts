import { Component, OnInit, Input } from '@angular/core';
import { DoctorService } from 'src/app/service/doctor.service';
@Component({
  selector: 'app-edit-doctor',
  templateUrl: './edit-doctor.component.html',
  styleUrls: ['./edit-doctor.component.css']
})
export class EditDoctorComponent implements OnInit {

  @Input()
  editDoctor!:any;
  constructor(private doctorService:DoctorService) { }

  ngOnInit(): void {
  }

  edit(doctor:any){
    doctor.id=this.editDoctor.id;
    console.log(doctor);
    this.doctorService.update(doctor).subscribe();

    location.reload();
  }
}
