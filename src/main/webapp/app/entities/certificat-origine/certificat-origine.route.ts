import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICertificatOrigine, CertificatOrigine } from 'app/shared/model/certificat-origine.model';
import { CertificatOrigineService } from './certificat-origine.service';
import { CertificatOrigineComponent } from './certificat-origine.component';
import { CertificatOrigineDetailComponent } from './certificat-origine-detail.component';
import { CertificatOrigineUpdateComponent } from './certificat-origine-update.component';

@Injectable({ providedIn: 'root' })
export class CertificatOrigineResolve implements Resolve<ICertificatOrigine> {
  constructor(private service: CertificatOrigineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICertificatOrigine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((certificatOrigine: HttpResponse<CertificatOrigine>) => {
          if (certificatOrigine.body) {
            return of(certificatOrigine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CertificatOrigine());
  }
}

export const certificatOrigineRoute: Routes = [
  {
    path: '',
    component: CertificatOrigineComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.certificatOrigine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CertificatOrigineDetailComponent,
    resolve: {
      certificatOrigine: CertificatOrigineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.certificatOrigine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CertificatOrigineUpdateComponent,
    resolve: {
      certificatOrigine: CertificatOrigineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.certificatOrigine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CertificatOrigineUpdateComponent,
    resolve: {
      certificatOrigine: CertificatOrigineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.certificatOrigine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
