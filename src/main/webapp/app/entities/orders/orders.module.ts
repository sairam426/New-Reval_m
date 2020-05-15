import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { OrdersComponent } from './orders.component';
import { OrdersDetailComponent } from './orders-detail.component';
import { OrdersUpdateComponent } from './orders-update.component';
import { OrdersDeleteDialogComponent } from './orders-delete-dialog.component';
import { ordersRoute } from './orders.route';
import { OrderSearchComponent } from './order-search.component';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(ordersRoute)],
  declarations: [OrdersComponent, OrdersDetailComponent, OrdersUpdateComponent, OrdersDeleteDialogComponent, OrderSearchComponent],
  entryComponents: [OrdersDeleteDialogComponent]
})
export class RevalOrdersModule {}
