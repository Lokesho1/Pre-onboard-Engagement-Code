import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddPatientComponent } from './component/add-patient/add-patient.component';
import { AdminDoctorComponent } from './component/admin-doctor/admin-doctor.component';
import { DoctorComponent } from './component/doctor/doctor.component';
import { LoginComponent } from './component/login/login.component';
import { ManagementComponent } from './component/management/management.component';
import { PatientComponent } from './component/patient/patient.component';
import { RegisterComponent } from './component/register/register.component';
import { AddDoctorComponent } from './component/add-doctor/add-doctor.component';
import { AddCheckupComponent } from './component/add-checkup/add-checkup.component';
import { DoctorLogComponent } from './component/doctor-log/doctor-log.component';
const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'patient',
    component: PatientComponent,
  },
  {
    path: 'doctor',
    component: DoctorComponent,
  },
  {
    path: 'admin',
    component: ManagementComponent,
  },
  {
    path: 'adminDoctor',
    component: AdminDoctorComponent,
  },
  {
    path: 'checkup',
    component: AddCheckupComponent,
  },
  {
    path: 'doctorlog',
    component: DoctorLogComponent,
  },
  {
    path: 'addPatient',
    component: AddPatientComponent,
    outlet: 'inner'
  },
  {
    path: 'addDoctor',
    component: AddDoctorComponent,
    outlet: 'inner'
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
