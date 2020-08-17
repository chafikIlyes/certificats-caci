import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGerant, Gerant } from 'app/shared/model/gerant.model';
import { GerantService } from './gerant.service';
import { GerantComponent } from './gerant.component';
import { GerantDetailComponent } from './gerant-detail.component';
import { GerantUpdateComponent } from './gerant-update.component';

@Injectable({ providedIn: 'root' })
export class GerantResolve implements Resolve<IGerant> {
  constructor(private service: GerantService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGerant> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gerant: HttpResponse<Gerant>) => {
          if (gerant.body) {
            return of(gerant.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Gerant());
  }
}

export const gerantRoute: Routes = [
  {
    path: '',
    component: GerantComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'certificatcaciApp.gerant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GerantDetailComponent,
    resolve: {
      gerant: GerantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.gerant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GerantUpdateComponent,
    resolve: {
      gerant: GerantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.gerant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GerantUpdateComponent,
    resolve: {
      gerant: GerantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.gerant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
