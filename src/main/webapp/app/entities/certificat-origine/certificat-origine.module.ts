import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CertificatcaciSharedModule } from 'app/shared/shared.module';
import { CertificatOrigineComponent } from './certificat-origine.component';
import { CertificatOrigineDetailComponent } from './certificat-origine-detail.component';
import { CertificatOrigineUpdateComponent } from './certificat-origine-update.component';
import { CertificatOrigineDeleteDialogComponent } from './certificat-origine-delete-dialog.component';
import { certificatOrigineRoute } from './certificat-origine.route';

@NgModule({
  imports: [CertificatcaciSharedModule, RouterModule.forChild(certificatOrigineRoute)],
  declarations: [
    CertificatOrigineComponent,
    CertificatOrigineDetailComponent,
    CertificatOrigineUpdateComponent,
    CertificatOrigineDeleteDialogComponent,
  ],
  entryComponents: [CertificatOrigineDeleteDialogComponent],
})
export class CertificatcaciCertificatOrigineModule {}
