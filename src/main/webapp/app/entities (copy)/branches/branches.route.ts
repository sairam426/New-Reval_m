import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBranches, Branches } from 'app/shared/model/branches.model';
import { BranchesService } from './branches.service';
import { BranchesComponent } from './branches.component';
import { BranchesDetailComponent } from './branches-detail.component';
import { BranchesUpdateComponent } from './branches-update.component';

@Injectable({ providedIn: 'root' })
export class BranchesResolve implements Resolve<IBranches> {
  constructor(private service: BranchesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBranches> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((branches: HttpResponse<Branches>) => {
          if (branches.body) {
            return of(branches.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Branches());
  }
}

export const branchesRoute: Routes = [
  {
    path: '',
    component: BranchesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.branches.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BranchesDetailComponent,
    resolve: {
      branches: BranchesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.branches.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BranchesUpdateComponent,
    resolve: {
      branches: BranchesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.branches.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BranchesUpdateComponent,
    resolve: {
      branches: BranchesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.branches.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
