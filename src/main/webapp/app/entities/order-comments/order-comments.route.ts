import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrderComments, OrderComments } from 'app/shared/model/order-comments.model';
import { OrderCommentsService } from './order-comments.service';
import { OrderCommentsComponent } from './order-comments.component';
import { OrderCommentsDetailComponent } from './order-comments-detail.component';
import { OrderCommentsUpdateComponent } from './order-comments-update.component';

@Injectable({ providedIn: 'root' })
export class OrderCommentsResolve implements Resolve<IOrderComments> {
  constructor(private service: OrderCommentsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrderComments> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((orderComments: HttpResponse<OrderComments>) => {
          if (orderComments.body) {
            return of(orderComments.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrderComments());
  }
}

export const orderCommentsRoute: Routes = [
  {
    path: '',
    component: OrderCommentsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.orderComments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderCommentsDetailComponent,
    resolve: {
      orderComments: OrderCommentsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.orderComments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderCommentsUpdateComponent,
    resolve: {
      orderComments: OrderCommentsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.orderComments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderCommentsUpdateComponent,
    resolve: {
      orderComments: OrderCommentsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'revalApp.orderComments.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
