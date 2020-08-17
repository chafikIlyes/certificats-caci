import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CertificatcaciSharedModule } from 'app/shared/shared.module';
import { ImportateurComponent } from './importateur.component';
import { ImportateurDetailComponent } from './importateur-detail.component';
import { ImportateurUpdateComponent } from './importateur-update.component';
import { ImportateurDeleteDialogComponent } from './importateur-delete-dialog.component';
import { importateurRoute } from './importateur.route';

@NgModule({
  imports: [CertificatcaciSharedModule, RouterModule.forChild(importateurRoute)],
  declarations: [ImportateurComponent, ImportateurDetailComponent, ImportateurUpdateComponent, ImportateurDeleteDialogComponent],
  entryComponents: [ImportateurDeleteDialogComponent],
})
export class CertificatcaciImportateurModule {}
