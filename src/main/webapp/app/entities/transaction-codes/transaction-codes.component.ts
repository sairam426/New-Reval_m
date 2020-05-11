import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITransactionCodes } from 'app/shared/model/transaction-codes.model';
import { TransactionCodesService } from './transaction-codes.service';
import { TransactionCodesDeleteDialogComponent } from './transaction-codes-delete-dialog.component';

@Component({
  selector: 'jhi-transaction-codes',
  templateUrl: './transaction-codes.component.html'
})
export class TransactionCodesComponent implements OnInit, OnDestroy {
  transactionCodes?: ITransactionCodes[];
  eventSubscriber?: Subscription;

  constructor(
    protected transactionCodesService: TransactionCodesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.transactionCodesService.query().subscribe((res: HttpResponse<ITransactionCodes[]>) => (this.transactionCodes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTransactionCodes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITransactionCodes): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTransactionCodes(): void {
    this.eventSubscriber = this.eventManager.subscribe('transactionCodesListModification', () => this.loadAll());
  }

  delete(transactionCodes: ITransactionCodes): void {
    const modalRef = this.modalService.open(TransactionCodesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.transactionCodes = transactionCodes;
  }
}
