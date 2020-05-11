import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { OrderCommentsComponent } from './order-comments.component';
import { OrderCommentsDetailComponent } from './order-comments-detail.component';
import { OrderCommentsUpdateComponent } from './order-comments-update.component';
import { OrderCommentsDeleteDialogComponent } from './order-comments-delete-dialog.component';
import { orderCommentsRoute } from './order-comments.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(orderCommentsRoute)],
  declarations: [OrderCommentsComponent, OrderCommentsDetailComponent, OrderCommentsUpdateComponent, OrderCommentsDeleteDialogComponent],
  entryComponents: [OrderCommentsDeleteDialogComponent]
})
export class RevalOrderCommentsModule {}
