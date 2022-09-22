import { Component, Input, OnInit } from '@angular/core';
import { CheckupSercviceService } from 'src/app/service/checkup-sercvice.service';

@Component({
  selector: 'app-add-checkup',
  templateUrl: './add-checkup.component.html',
  styleUrls: ['./add-checkup.component.css']
})
export class AddCheckupComponent implements OnInit {

  @Input()
  patient: any;
  checkup: any;
  constructor(private checkupService:CheckupSercviceService) { }

  ngOnInit(): void {
    
  }
  
  add(checkup: any) {
    console.log("p1",this.patient);
    let c = {
      checkUpDate : new Date(),
      details:checkup.details,
      doctorId:this.patient.doctor.id,
      doctorName:this.patient.doctor.name,
      medicine:checkup.medicine,
      patientId:this.patient.id
    }
    this.checkupService.addcheckup(c).subscribe();
    console.log("c",c);
    location.reload();
  }
}
