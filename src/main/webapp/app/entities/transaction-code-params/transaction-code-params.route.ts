import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITransactionCodeParams, TransactionCodeParams } from 'app/shared/model/transaction-code-params.model';
import { TransactionCodeParamsService } from './transaction-code-params.service';
import { TransactionCodeParamsComponent } from './transaction-code-params.component';
import { TransactionCodeParamsDetailComponent } from './transaction-code-params-detail.component';
import { TransactionCodeParamsUpdateComponent } from './transaction-code-params-update.component';

@Injectable({ providedIn: 'root' })
export class TransactionCodeParamsResolve implements Resolve<ITransactionCodeParams> {
  constructor(private service: TransactionCodeParamsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITransactionCodeParams> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((transactionCodeParams: HttpResponse<TransactionCodeParams>) => {
          if (transactionCodeParams.body) {
            return of(transactionCodeParams.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TransactionCodeParams());
  }
}

export const transactionCodeParamsRoute: Routes = [
  {
    path: '',
    component: TransactionCodeParamsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.transactionCodeParams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TransactionCodeParamsDetailComponent,
    resolve: {
      transactionCodeParams: TransactionCodeParamsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.transactionCodeParams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TransactionCodeParamsUpdateComponent,
    resolve: {
      transactionCodeParams: TransactionCodeParamsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.transactionCodeParams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TransactionCodeParamsUpdateComponent,
    resolve: {
      transactionCodeParams: TransactionCodeParamsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.transactionCodeParams.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
