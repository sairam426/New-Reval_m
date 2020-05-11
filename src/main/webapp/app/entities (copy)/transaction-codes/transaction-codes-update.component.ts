import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITransactionCodes, TransactionCodes } from 'app/shared/model/transaction-codes.model';
import { TransactionCodesService } from './transaction-codes.service';

@Component({
  selector: 'jhi-transaction-codes-update',
  templateUrl: './transaction-codes-update.component.html'
})
export class TransactionCodesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tcdCode: [],
    tcdEntityGroupCd: [],
    tcdDesc: [],
    tcdEnabledInd: []
  });

  constructor(
    protected transactionCodesService: TransactionCodesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transactionCodes }) => {
      this.updateForm(transactionCodes);
    });
  }

  updateForm(transactionCodes: ITransactionCodes): void {
    this.editForm.patchValue({
      id: transactionCodes.id,
      tcdCode: transactionCodes.tcdCode,
      tcdEntityGroupCd: transactionCodes.tcdEntityGroupCd,
      tcdDesc: transactionCodes.tcdDesc,
      tcdEnabledInd: transactionCodes.tcdEnabledInd
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transactionCodes = this.createFromForm();
    if (transactionCodes.id !== undefined) {
      this.subscribeToSaveResponse(this.transactionCodesService.update(transactionCodes));
    } else {
      this.subscribeToSaveResponse(this.transactionCodesService.create(transactionCodes));
    }
  }

  private createFromForm(): ITransactionCodes {
    return {
      ...new TransactionCodes(),
      id: this.editForm.get(['id'])!.value,
      tcdCode: this.editForm.get(['tcdCode'])!.value,
      tcdEntityGroupCd: this.editForm.get(['tcdEntityGroupCd'])!.value,
      tcdDesc: this.editForm.get(['tcdDesc'])!.value,
      tcdEnabledInd: this.editForm.get(['tcdEnabledInd'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransactionCodes>>): void {
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
