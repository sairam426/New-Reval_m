import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISystemAccess, SystemAccess } from 'app/shared/model/system-access.model';
import { SystemAccessService } from './system-access.service';
import { SystemAccessComponent } from './system-access.component';
import { SystemAccessDetailComponent } from './system-access-detail.component';
import { SystemAccessUpdateComponent } from './system-access-update.component';

@Injectable({ providedIn: 'root' })
export class SystemAccessResolve implements Resolve<ISystemAccess> {
  constructor(private service: SystemAccessService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISystemAccess> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((systemAccess: HttpResponse<SystemAccess>) => {
          if (systemAccess.body) {
            return of(systemAccess.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SystemAccess());
  }
}

export const systemAccessRoute: Routes = [
  {
    path: '',
    component: SystemAccessComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.systemAccess.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SystemAccessDetailComponent,
    resolve: {
      systemAccess: SystemAccessResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.systemAccess.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SystemAccessUpdateComponent,
    resolve: {
      systemAccess: SystemAccessResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.systemAccess.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SystemAccessUpdateComponent,
    resolve: {
      systemAccess: SystemAccessResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.systemAccess.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
