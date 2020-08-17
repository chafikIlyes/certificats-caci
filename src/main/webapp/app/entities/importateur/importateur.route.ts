import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IImportateur, Importateur } from 'app/shared/model/importateur.model';
import { ImportateurService } from './importateur.service';
import { ImportateurComponent } from './importateur.component';
import { ImportateurDetailComponent } from './importateur-detail.component';
import { ImportateurUpdateComponent } from './importateur-update.component';

@Injectable({ providedIn: 'root' })
export class ImportateurResolve implements Resolve<IImportateur> {
  constructor(private service: ImportateurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IImportateur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((importateur: HttpResponse<Importateur>) => {
          if (importateur.body) {
            return of(importateur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Importateur());
  }
}

export const importateurRoute: Routes = [
  {
    path: '',
    component: ImportateurComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'certificatcaciApp.importateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ImportateurDetailComponent,
    resolve: {
      importateur: ImportateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.importateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ImportateurUpdateComponent,
    resolve: {
      importateur: ImportateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.importateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ImportateurUpdateComponent,
    resolve: {
      importateur: ImportateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'certificatcaciApp.importateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
