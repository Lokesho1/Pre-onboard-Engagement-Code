import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'doc'
})
export class DoctorPipe implements PipeTransform {

  transform(value:any){
     return value.name+", "+value.specialization;

  }

}