import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CertificatcaciSharedModule } from 'app/shared/shared.module';
import { CertificatcaciCoreModule } from 'app/core/core.module';
import { CertificatcaciAppRoutingModule } from './app-routing.module';
import { CertificatcaciHomeModule } from './home/home.module';
import { CertificatcaciEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CertificatcaciSharedModule,
    CertificatcaciCoreModule,
    CertificatcaciHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CertificatcaciEntityModule,
    CertificatcaciAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class CertificatcaciAppModule {}
