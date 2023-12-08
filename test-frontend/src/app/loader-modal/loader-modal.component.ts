import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { LoadingController } from '@ionic/angular';
import { LoaderContentComponent } from '../loader-content/loader-content.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-loader-modal',
  templateUrl: './loader-modal.component.html',
  styleUrls: ['./loader-modal.component.scss'],
})
export class LoaderModalComponent  implements OnInit {

  config:any = [];
  totalTime:number = 0;

  constructor(private modalCtrl: ModalController,private router:Router) {
    this.config[0] = [
      {
        "image" : "img1.jpg",
        "text"  : "recomendación1 xqrqrrew",
        "duration" : 10
      },
      {
        "image" : "img2.jpg",
        "text"  : "recomendacion2 ewqcewq",
        "duration" : 10
      },
      {
        "image" : "img3.jpg",
        "text"  : "recomendacion3 ewqcewq",
        "duration" : 10
      }
    ];

    this.config[1] = [
      {
        "image" : "img3.jpg",
        "text"  : "recomendación3 3333",
        "duration" : 6
      },
      {
        "image" : "img2.jpg",
        "text"  : "recomendacion2 2222",
        "duration" : 6
      },
      {
        "image" : "img1.jpg",
        "text"  : "recomendacion1 11111",
        "duration" : 4
      }
    ];
  }

  ngOnInit() {
  }

  async openModal(nconfig:number) {
    
    const modal = await this.modalCtrl.create({
      component: LoaderContentComponent,
      componentProps: {
        data: nconfig,
        config: this.config
      },
      backdropDismiss: false
    });

    await modal.present();

    for (let i=0; i< this.config[nconfig].length ; i++ ){
      this.totalTime += this.config[nconfig][i].duration;
    }
    
    setTimeout(() => {
            
        modal.dismiss({
          dismissed: true
        });
        this.router.navigateByUrl('salir');
    },this.totalTime * 1000 );

  }

}
