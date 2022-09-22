import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AuthorizationService } from 'src/app/service/authorization.service'; 
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user:User=new User("","");
  valid:boolean=true;
  constructor(private authorizationService:AuthorizationService) { }

  ngOnInit(): void {
  }

  register(user:Object) {
    console.log(user);
    this.authorizationService.register(user).subscribe({ 
      next: (data:any) =>{
      console.log(data);
      this.valid=true;
      },
      
      error: (error:any)=>{
        this.valid=false;

      },
    });
  }

}
