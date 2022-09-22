import { Injectable } from '@angular/core';  
  
@Injectable()  
export class DataService {  
  
  private data:any = {};  
  
 setOption(option:any, value:any) {      
    this.data[option] = value;  
  }  
  
  getOption() {  
    return this.data;  
  }  
}  