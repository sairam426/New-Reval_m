import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITransactionCodes, TransactionCodes } from 'app/shared/model/transaction-codes.model';
import { TransactionCodesService } from './transaction-codes.service';
import { TransactionCodesComponent } from './transaction-codes.component';
import { TransactionCodesDetailComponent } from './transaction-codes-detail.component';
import { TransactionCodesUpdateComponent } from './transaction-codes-update.component';

@Injectable({ providedIn: 'root' })
export class TransactionCodesResolve implements Resolve<ITransactionCodes> {
  constructor(private service: TransactionCodesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITransactionCodes> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((transactionCodes: HttpResponse<TransactionCodes>) => {
          if (transactionCodes.body) {
            return of(transactionCodes.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TransactionCodes());
  }
}

export const transactionCodesRoute: Routes = [
  {
    path: '',
    component: TransactionCodesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.transactionCodes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TransactionCodesDetailComponent,
    resolve: {
      transactionCodes: TransactionCodesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.transactionCodes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TransactionCodesUpdateComponent,
    resolve: {
      transactionCodes: TransactionCodesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.transactionCodes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TransactionCodesUpdateComponent,
    resolve: {
      transactionCodes: TransactionCodesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.transactionCodes.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
