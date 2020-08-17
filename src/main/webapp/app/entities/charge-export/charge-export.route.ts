import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IChargeExport, ChargeExport } from 'app/shared/model/charge-export.model';
import { ChargeExportService } from './charge-export.service';
import { ChargeExportComponent } from './charge-export.component';
import { ChargeExportDetailComponent } from './charge-export-detail.component';
import { ChargeExportUpdateComponent } from './charge-export-update.component';

@Injectable({ providedIn: 'root' })
export class ChargeExportResolve implements Resolve<IChargeExport> {
  constructor(private service: ChargeExportService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IChargeExport> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((chargeExport: HttpResponse<ChargeExport>) => {
          if (chargeExport.body) {
            return of(chargeExport.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ChargeExport());
  }
}

export const chargeExportRoute: Routes = [
  {
    path: '',
    component: ChargeExportComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'certificatcaciApp.chargeExport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ChargeExportDetailComponent,
    resolve: {
      chargeExport: ChargeExportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.chargeExport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ChargeExportUpdateComponent,
    resolve: {
      chargeExport: ChargeExportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.chargeExport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ChargeExportUpdateComponent,
    resolve: {
      chargeExport: ChargeExportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.chargeExport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
