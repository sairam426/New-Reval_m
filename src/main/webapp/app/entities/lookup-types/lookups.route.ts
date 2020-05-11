import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILookups, Lookups } from 'app/shared/model/lookups.model';
import { LookupsService } from './lookups.service';
import { LookupsComponent } from './lookups.component';
import { LookupsDetailComponent } from './lookups-detail.component';
import { LookupsUpdateComponent } from './lookups-update.component';

@Injectable({ providedIn: 'root' })
export class LookupsResolve implements Resolve<ILookups> {
  constructor(private service: LookupsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILookups> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lookups: HttpResponse<Lookups>) => {
          if (lookups.body) {
            return of(lookups.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Lookups());
  }
}

export const lookupsRoute: Routes = [
  {
    path: 'lookups',
    component: LookupsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.lookups.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LookupsDetailComponent,
    resolve: {
      lookups: LookupsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.lookups.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LookupsUpdateComponent,
    resolve: {
      lookups: LookupsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.lookups.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LookupsUpdateComponent,
    resolve: {
      lookups: LookupsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.lookups.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
