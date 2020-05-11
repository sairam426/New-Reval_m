import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransactionCodes } from 'app/shared/model/transaction-codes.model';
import { TransactionCodesService } from './transaction-codes.service';

@Component({
  templateUrl: './transaction-codes-delete-dialog.component.html'
})
export class TransactionCodesDeleteDialogComponent {
  transactionCodes?: ITransactionCodes;

  constructor(
    protected transactionCodesService: TransactionCodesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.transactionCodesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('transactionCodesListModification');
      this.activeModal.close();
    });
  }
}
