import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { TransactionCodesComponent } from './transaction-codes.component';
import { TransactionCodesDetailComponent } from './transaction-codes-detail.component';
import { TransactionCodesUpdateComponent } from './transaction-codes-update.component';
import { TransactionCodesDeleteDialogComponent } from './transaction-codes-delete-dialog.component';
import { transactionCodesRoute } from './transaction-codes.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(transactionCodesRoute)],
  declarations: [
    TransactionCodesComponent,
    TransactionCodesDetailComponent,
    TransactionCodesUpdateComponent,
    TransactionCodesDeleteDialogComponent
  ],
  entryComponents: [TransactionCodesDeleteDialogComponent]
})
export class RevalTransactionCodesModule {}
