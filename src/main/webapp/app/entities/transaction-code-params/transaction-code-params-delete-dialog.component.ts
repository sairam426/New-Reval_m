import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransactionCodeParams } from 'app/shared/model/transaction-code-params.model';
import { TransactionCodeParamsService } from './transaction-code-params.service';

@Component({
  templateUrl: './transaction-code-params-delete-dialog.component.html'
})
export class TransactionCodeParamsDeleteDialogComponent {
  transactionCodeParams?: ITransactionCodeParams;

  constructor(
    protected transactionCodeParamsService: TransactionCodeParamsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.transactionCodeParamsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('transactionCodeParamsListModification');
      this.activeModal.close();
    });
  }
}
