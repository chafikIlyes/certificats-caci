import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CertificatcaciSharedModule } from 'app/shared/shared.module';
import { GerantComponent } from './gerant.component';
import { GerantDetailComponent } from './gerant-detail.component';
import { GerantUpdateComponent } from './gerant-update.component';
import { GerantDeleteDialogComponent } from './gerant-delete-dialog.component';
import { gerantRoute } from './gerant.route';

@NgModule({
  imports: [CertificatcaciSharedModule, RouterModule.forChild(gerantRoute)],
  declarations: [GerantComponent, GerantDetailComponent, GerantUpdateComponent, GerantDeleteDialogComponent],
  entryComponents: [GerantDeleteDialogComponent],
})
export class CertificatcaciGerantModule {}
