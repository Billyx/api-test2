import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-loader-content',
  templateUrl: './loader-content.component.html',
  styleUrls: ['./loader-content.component.scss'],
})
export class LoaderContentComponent  implements OnInit {

  @Input() data: any;
  @Input() config:any = [];
  public text:string ="";
  public image:string ="";
  private count:number = 0;
  
  constructor(private router:Router) {      
  }

  async ngOnInit() {
    this.text = this.config[this.data][0].text;
    this.image = this.config[this.data][0].image;
     
      await this.assignData(this.config[this.data]);        
     
  }

  async assignData(element:any){

    for (let i=0; i< element.length ; i++ ){
      
      if(i < element.length - 1){ 
        await this.setElement(element[i+1])
      }

    }
  }

  async setElement(element:any){

    return new Promise( resolve => {
      
      setTimeout(() => {
        this.text = element.text;
        this.image = element.image;
        console.log(element.duration * 1000)
        resolve(element);
      }, element.duration * 1000);
      
    }) 
  }
}
