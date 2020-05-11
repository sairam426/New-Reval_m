import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITransactionCodeParams } from 'app/shared/model/transaction-code-params.model';
import { TransactionCodeParamsService } from './transaction-code-params.service';
import { TransactionCodeParamsDeleteDialogComponent } from './transaction-code-params-delete-dialog.component';

@Component({
  selector: 'jhi-transaction-code-params',
  templateUrl: './transaction-code-params.component.html'
})
export class TransactionCodeParamsComponent implements OnInit, OnDestroy {
  transactionCodeParams?: ITransactionCodeParams[];
  eventSubscriber?: Subscription;

  constructor(
    protected transactionCodeParamsService: TransactionCodeParamsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.transactionCodeParamsService
      .query()
      .subscribe((res: HttpResponse<ITransactionCodeParams[]>) => (this.transactionCodeParams = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTransactionCodeParams();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITransactionCodeParams): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTransactionCodeParams(): void {
    this.eventSubscriber = this.eventManager.subscribe('transactionCodeParamsListModification', () => this.loadAll());
  }

  delete(transactionCodeParams: ITransactionCodeParams): void {
    const modalRef = this.modalService.open(TransactionCodeParamsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.transactionCodeParams = transactionCodeParams;
  }
}
