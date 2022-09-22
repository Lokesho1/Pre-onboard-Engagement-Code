import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthorizationService } from 'src/app/service/authorization.service';
import { DataService } from 'src/app/service/dataService';
import { DoctorService } from 'src/app/service/doctor.service';
import { PatientService } from 'src/app/service/patient.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  d:boolean=false;
  // user:User = new User("","");
  invalid:boolean=false;
  // details!:any;
  constructor(private authorizationService:AuthorizationService, private router:Router,private doctorService:DoctorService,private patientService:PatientService, private dataService:DataService) { }

  ngOnInit(): void {
  }

  login(user:Object) {
    console.log(user);
    this.authorizationService.authenticate(user).subscribe({ 
      next: (data:any) =>{
      console.log(data);
      this.invalid=false;
      localStorage.setItem('token', data.token);
      localStorage.setItem('role', data.user.role);
      role:String=data.user.role;
      this.authorizationService.role=data.user.role;
      if(data.user.role==="patient"){
        // localStorage.setItem("patientId",data.user.identity);
        this.dataService.setOption("patientId",data.user.identity);
        this.router.navigate(['patient']);

        // this.patientService.get(data.user.identity).subscribe({
        //   next:(data:any)=>{
        //     // this.details=JSON.parse(data);
        //     // console.log(this.details);
        //     // const temp={state:JSON.parse(data)};
        //     // console.log("temp ",temp);
        //     data=JSON.parse(data);
        //     console.log(data);
        //     const c={
        //       id:data.id,
        //       name:data.name,
        //       admissionDate:data.admissionDate,
        //       doctorId:data.doctor.id,
        //       doctorName:data.doctor.name,
        //     }
        //     console.log(c);
            
        //     this.router.navigateByUrl('/patient',{state:{pat:JSON.stringify(c)}});
        //   }
        // });
        
      }
      else if(data.user.role==="doctor"){
        this.dataService.setOption("doctorId",data.user.identity);
        this.d=true;
        this.router.navigate(['doctorlog']);
      }
      else if(data.user.role==="admin"){
        this.router.navigate(['admin']);
      }
      else{
        this.invalid=true;
        this.router.navigate(['/login']);
      }
      },
      
      error: (error:any)=>{
        this.invalid=true;

      },
    });
  }

}
