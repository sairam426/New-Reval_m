import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { OrderDocumentsComponent } from './order-documents.component';
import { OrderDocumentsDetailComponent } from './order-documents-detail.component';
import { OrderDocumentsUpdateComponent } from './order-documents-update.component';
import { OrderDocumentsDeleteDialogComponent } from './order-documents-delete-dialog.component';
import { orderDocumentsRoute } from './order-documents.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(orderDocumentsRoute)],
  declarations: [
    OrderDocumentsComponent,
    OrderDocumentsDetailComponent,
    OrderDocumentsUpdateComponent,
    OrderDocumentsDeleteDialogComponent
  ],
  entryComponents: [OrderDocumentsDeleteDialogComponent]
})
export class RevalOrderDocumentsModule {}
