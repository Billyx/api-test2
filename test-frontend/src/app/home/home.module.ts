import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { HomePage } from './home.page';
import { LoaderModalComponent } from '../loader-modal/loader-modal.component'
import { HomePageRoutingModule } from './home-routing.module';
import { DialogModule } from 'primeng/dialog';
import { LoaderContentComponent } from '../loader-content/loader-content.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    HomePageRoutingModule,
    DialogModule
  ],
  declarations: [HomePage,
                  LoaderModalComponent,
                  LoaderContentComponent]
})
export class HomePageModule {}
