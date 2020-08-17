import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CertificatcaciSharedModule } from 'app/shared/shared.module';
import { ChargeExportComponent } from './charge-export.component';
import { ChargeExportDetailComponent } from './charge-export-detail.component';
import { ChargeExportUpdateComponent } from './charge-export-update.component';
import { ChargeExportDeleteDialogComponent } from './charge-export-delete-dialog.component';
import { chargeExportRoute } from './charge-export.route';

@NgModule({
  imports: [CertificatcaciSharedModule, RouterModule.forChild(chargeExportRoute)],
  declarations: [ChargeExportComponent, ChargeExportDetailComponent, ChargeExportUpdateComponent, ChargeExportDeleteDialogComponent],
  entryComponents: [ChargeExportDeleteDialogComponent],
})
export class CertificatcaciChargeExportModule {}
