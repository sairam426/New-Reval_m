import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILookupTypes, LookupTypes } from 'app/shared/model/lookup-types.model';
import { LookupTypesService } from './lookup-types.service';
import { LookupTypesComponent } from './lookup-types.component';
import { LookupTypesDetailComponent } from './lookup-types-detail.component';
import { LookupTypesUpdateComponent } from './lookup-types-update.component';

@Injectable({ providedIn: 'root' })
export class LookupTypesResolve implements Resolve<ILookupTypes> {
  constructor(private service: LookupTypesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILookupTypes> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lookupTypes: HttpResponse<LookupTypes>) => {
          if (lookupTypes.body) {
            return of(lookupTypes.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LookupTypes());
  }
}

export const lookupTypesRoute: Routes = [
  {
    path: '',
    component: LookupTypesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.lookupTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LookupTypesDetailComponent,
    resolve: {
      lookupTypes: LookupTypesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.lookupTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LookupTypesUpdateComponent,
    resolve: {
      lookupTypes: LookupTypesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.lookupTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LookupTypesUpdateComponent,
    resolve: {
      lookupTypes: LookupTypesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.lookupTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
