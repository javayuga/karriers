import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WebuiSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [WebuiSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class WebuiHomeModule {}
