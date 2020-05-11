import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrderDocuments, OrderDocuments } from 'app/shared/model/order-documents.model';
import { OrderDocumentsService } from './order-documents.service';
import { OrderDocumentsComponent } from './order-documents.component';
import { OrderDocumentsDetailComponent } from './order-documents-detail.component';
import { OrderDocumentsUpdateComponent } from './order-documents-update.component';

@Injectable({ providedIn: 'root' })
export class OrderDocumentsResolve implements Resolve<IOrderDocuments> {
  constructor(private service: OrderDocumentsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrderDocuments> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((orderDocuments: HttpResponse<OrderDocuments>) => {
          if (orderDocuments.body) {
            return of(orderDocuments.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrderDocuments());
  }
}

export const orderDocumentsRoute: Routes = [
  {
    path: '',
    component: OrderDocumentsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.orderDocuments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderDocumentsDetailComponent,
    resolve: {
      orderDocuments: OrderDocumentsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.orderDocuments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderDocumentsUpdateComponent,
    resolve: {
      orderDocuments: OrderDocumentsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.orderDocuments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderDocumentsUpdateComponent,
    resolve: {
      orderDocuments: OrderDocumentsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.orderDocuments.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
