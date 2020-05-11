import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrganizations, Organizations } from 'app/shared/model/organizations.model';
import { OrganizationsService } from './organizations.service';
import { OrganizationsComponent } from './organizations.component';
import { OrganizationsDetailComponent } from './organizations-detail.component';
import { OrganizationsUpdateComponent } from './organizations-update.component';

@Injectable({ providedIn: 'root' })
export class OrganizationsResolve implements Resolve<IOrganizations> {
  constructor(private service: OrganizationsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrganizations> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((organizations: HttpResponse<Organizations>) => {
          if (organizations.body) {
            return of(organizations.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Organizations());
  }
}

export const organizationsRoute: Routes = [
  {
    path: '',
    component: OrganizationsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.organizations.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrganizationsDetailComponent,
    resolve: {
      organizations: OrganizationsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.organizations.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrganizationsUpdateComponent,
    resolve: {
      organizations: OrganizationsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.organizations.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrganizationsUpdateComponent,
    resolve: {
      organizations: OrganizationsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.organizations.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
