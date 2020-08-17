import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'entreprise',
        loadChildren: () => import('./entreprise/entreprise.module').then(m => m.CertificatcaciEntrepriseModule),
      },
      {
        path: 'gerant',
        loadChildren: () => import('./gerant/gerant.module').then(m => m.CertificatcaciGerantModule),
      },
      {
        path: 'charge-export',
        loadChildren: () => import('./charge-export/charge-export.module').then(m => m.CertificatcaciChargeExportModule),
      },
      {
        path: 'importateur',
        loadChildren: () => import('./importateur/importateur.module').then(m => m.CertificatcaciImportateurModule),
      },
      {
        path: 'produit',
        loadChildren: () => import('./produit/produit.module').then(m => m.CertificatcaciProduitModule),
      },
      {
        path: 'certificat-origine',
        loadChildren: () => import('./certificat-origine/certificat-origine.module').then(m => m.CertificatcaciCertificatOrigineModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CertificatcaciEntityModule {}
