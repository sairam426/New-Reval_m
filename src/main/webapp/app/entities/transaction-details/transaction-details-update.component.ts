import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITransactionDetails, TransactionDetails } from 'app/shared/model/transaction-details.model';
import { TransactionDetailsService } from './transaction-details.service';
import { ITransactions } from 'app/shared/model/transactions.model';
import { TransactionsService } from 'app/entities/transactions/transactions.service';

@Component({
  selector: 'jhi-transaction-details-update',
  templateUrl: './transaction-details-update.component.html'
})
export class TransactionDetailsUpdateComponent implements OnInit {
  isSaving = false;
  transactions: ITransactions[] = [];

  editForm = this.fb.group({
    id: [],
    txdPrmCode: [null, [Validators.required, Validators.maxLength(30)]],
    txdValue: [null, [Validators.maxLength(240)]],
    transaction: []
  });

  constructor(
    protected transactionDetailsService: TransactionDetailsService,
    protected transactionsService: TransactionsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transactionDetails }) => {
      this.updateForm(transactionDetails);

      this.transactionsService.query().subscribe((res: HttpResponse<ITransactions[]>) => (this.transactions = res.body || []));
    });
  }

  updateForm(transactionDetails: ITransactionDetails): void {
    this.editForm.patchValue({
      id: transactionDetails.id,
      txdPrmCode: transactionDetails.txdPrmCode,
      txdValue: transactionDetails.txdValue,
      transaction: transactionDetails.transaction
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transactionDetails = this.createFromForm();
    if (transactionDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.transactionDetailsService.update(transactionDetails));
    } else {
      this.subscribeToSaveResponse(this.transactionDetailsService.create(transactionDetails));
    }
  }

  private createFromForm(): ITransactionDetails {
    return {
      ...new TransactionDetails(),
      id: this.editForm.get(['id'])!.value,
      txdPrmCode: this.editForm.get(['txdPrmCode'])!.value,
      txdValue: this.editForm.get(['txdValue'])!.value,
      transaction: this.editForm.get(['transaction'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransactionDetails>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ITransactions): any {
    return item.id;
  }
}
