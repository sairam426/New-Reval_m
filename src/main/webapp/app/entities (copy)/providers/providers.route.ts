import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProviders, Providers } from 'app/shared/model/providers.model';
import { ProvidersService } from './providers.service';
import { ProvidersComponent } from './providers.component';
import { ProvidersDetailComponent } from './providers-detail.component';
import { ProvidersUpdateComponent } from './providers-update.component';

@Injectable({ providedIn: 'root' })
export class ProvidersResolve implements Resolve<IProviders> {
  constructor(private service: ProvidersService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProviders> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((providers: HttpResponse<Providers>) => {
          if (providers.body) {
            return of(providers.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Providers());
  }
}

export const providersRoute: Routes = [
  {
    path: '',
    component: ProvidersComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.providers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProvidersDetailComponent,
    resolve: {
      providers: ProvidersResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.providers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProvidersUpdateComponent,
    resolve: {
      providers: ProvidersResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.providers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProvidersUpdateComponent,
    resolve: {
      providers: ProvidersResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.providers.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
