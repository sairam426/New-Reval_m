import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { TransactionCodeParamsComponent } from './transaction-code-params.component';
import { TransactionCodeParamsDetailComponent } from './transaction-code-params-detail.component';
import { TransactionCodeParamsUpdateComponent } from './transaction-code-params-update.component';
import { TransactionCodeParamsDeleteDialogComponent } from './transaction-code-params-delete-dialog.component';
import { transactionCodeParamsRoute } from './transaction-code-params.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(transactionCodeParamsRoute)],
  declarations: [
    TransactionCodeParamsComponent,
    TransactionCodeParamsDetailComponent,
    TransactionCodeParamsUpdateComponent,
    TransactionCodeParamsDeleteDialogComponent
  ],
  entryComponents: [TransactionCodeParamsDeleteDialogComponent]
})
export class RevalTransactionCodeParamsModule {}
