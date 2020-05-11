import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransactionCodeParams } from 'app/shared/model/transaction-code-params.model';

@Component({
  selector: 'jhi-transaction-code-params-detail',
  templateUrl: './transaction-code-params-detail.component.html'
})
export class TransactionCodeParamsDetailComponent implements OnInit {
  transactionCodeParams: ITransactionCodeParams | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transactionCodeParams }) => (this.transactionCodeParams = transactionCodeParams));
  }

  previousState(): void {
    window.history.back();
  }
}
