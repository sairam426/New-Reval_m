import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICompanies, Companies } from 'app/shared/model/companies.model';
import { CompaniesService } from './companies.service';
import { CompaniesComponent } from './companies.component';
import { CompaniesDetailComponent } from './companies-detail.component';
import { CompaniesUpdateComponent } from './companies-update.component';

@Injectable({ providedIn: 'root' })
export class CompaniesResolve implements Resolve<ICompanies> {
  constructor(private service: CompaniesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompanies> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((companies: HttpResponse<Companies>) => {
          if (companies.body) {
            return of(companies.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Companies());
  }
}

export const companiesRoute: Routes = [
  {
    path: '',
    component: CompaniesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.companies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CompaniesDetailComponent,
    resolve: {
      companies: CompaniesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.companies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CompaniesUpdateComponent,
    resolve: {
      companies: CompaniesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.companies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CompaniesUpdateComponent,
    resolve: {
      companies: CompaniesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.companies.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
