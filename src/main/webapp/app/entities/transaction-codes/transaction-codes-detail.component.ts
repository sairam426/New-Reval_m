import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransactionCodes } from 'app/shared/model/transaction-codes.model';

@Component({
  selector: 'jhi-transaction-codes-detail',
  templateUrl: './transaction-codes-detail.component.html'
})
export class TransactionCodesDetailComponent implements OnInit {
  transactionCodes: ITransactionCodes | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transactionCodes }) => (this.transactionCodes = transactionCodes));
  }

  previousState(): void {
    window.history.back();
  }
}
