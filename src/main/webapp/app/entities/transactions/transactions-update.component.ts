import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITransactions, Transactions } from 'app/shared/model/transactions.model';
import { TransactionsService } from './transactions.service';

@Component({
  selector: 'jhi-transactions-update',
  templateUrl: './transactions-update.component.html'
})
export class TransactionsUpdateComponent implements OnInit {
  isSaving = false;
  txnPostDtDp: any;

  editForm = this.fb.group({
    id: [],
    txnEntityId: [null, [Validators.required]],
    txnEntityNbr: [null, [Validators.required]],
    txnTcdCode: [null, [Validators.required]],
    txnPostDt: [null, [Validators.required]],
    txnStatusCd: [null, [Validators.required, Validators.maxLength(30)]]
  });

  constructor(protected transactionsService: TransactionsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transactions }) => {
      this.updateForm(transactions);
    });
  }

  updateForm(transactions: ITransactions): void {
    this.editForm.patchValue({
      id: transactions.id,
      txnEntityId: transactions.txnEntityId,
      txnEntityNbr: transactions.txnEntityNbr,
      txnTcdCode: transactions.txnTcdCode,
      txnPostDt: transactions.txnPostDt,
      txnStatusCd: transactions.txnStatusCd
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transactions = this.createFromForm();
    if (transactions.id !== undefined) {
      this.subscribeToSaveResponse(this.transactionsService.update(transactions));
    } else {
      this.subscribeToSaveResponse(this.transactionsService.create(transactions));
    }
  }

  private createFromForm(): ITransactions {
    return {
      ...new Transactions(),
      id: this.editForm.get(['id'])!.value,
      txnEntityId: this.editForm.get(['txnEntityId'])!.value,
      txnEntityNbr: this.editForm.get(['txnEntityNbr'])!.value,
      txnTcdCode: this.editForm.get(['txnTcdCode'])!.value,
      txnPostDt: this.editForm.get(['txnPostDt'])!.value,
      txnStatusCd: this.editForm.get(['txnStatusCd'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransactions>>): void {
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
}
