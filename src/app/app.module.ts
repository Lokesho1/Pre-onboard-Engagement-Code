import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { PatientComponent } from './component/patient/patient.component';
import { RegisterComponent } from './component/register/register.component';
import { ManagementComponent } from './component/management/management.component';
import { DoctorComponent } from './component/doctor/doctor.component';
import { AddPatientComponent } from './component/add-patient/add-patient.component';
import { DoctorPipe } from './pipe/doctorPipe';
import { AdminDoctorComponent } from './component/admin-doctor/admin-doctor.component';
import { AddDoctorComponent } from './component/add-doctor/add-doctor.component';
import { DataService } from './service/dataService';
import { EditPatientComponent } from './component/edit-patient/edit-patient.component';
import { EditDoctorComponent } from './component/edit-doctor/edit-doctor.component';
import { AuthorizationService } from './service/authorization.service';
import { TokenInterceptorService } from './service/token-interceptor.service';
import { DoctorService } from './service/doctor.service';
import { PatientService } from './service/patient.service';
import { AddCheckupComponent } from './component/add-checkup/add-checkup.component';
import { DoctorLogComponent } from './component/doctor-log/doctor-log.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PatientComponent,
    RegisterComponent,
    ManagementComponent,
    DoctorComponent,
    AddPatientComponent,
    DoctorPipe,
    AdminDoctorComponent,
    AddDoctorComponent,
    EditPatientComponent,
    EditDoctorComponent,
    AddCheckupComponent,
    DoctorLogComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],

  providers: [
    AuthorizationService,DoctorService,PatientService, {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    DataService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
