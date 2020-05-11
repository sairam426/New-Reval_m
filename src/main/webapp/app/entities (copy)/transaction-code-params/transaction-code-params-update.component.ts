import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITransactionCodeParams, TransactionCodeParams } from 'app/shared/model/transaction-code-params.model';
import { TransactionCodeParamsService } from './transaction-code-params.service';
import { ITransactionCodes } from 'app/shared/model/transaction-codes.model';
import { TransactionCodesService } from 'app/entities/transaction-codes/transaction-codes.service';

@Component({
  selector: 'jhi-transaction-code-params-update',
  templateUrl: './transaction-code-params-update.component.html'
})
export class TransactionCodeParamsUpdateComponent implements OnInit {
  isSaving = false;
  transactioncodes: ITransactionCodes[] = [];

  editForm = this.fb.group({
    id: [],
    tcpTcdCode: [null, [Validators.required, Validators.maxLength(30)]],
    tcpParamName: [null, [Validators.required, Validators.maxLength(30)]],
    tcpParamDesc: [null, [Validators.required, Validators.maxLength(80)]],
    tcpEnabledInd: [null, [Validators.required]],
    tcpParamDataTypeCd: [null, [Validators.required, Validators.maxLength(30)]],
    tcpParamLength: [null, [Validators.required, Validators.maxLength(30)]],
    tcpDefaultValue: [null, [Validators.required, Validators.maxLength(240)]],
    tcpParamLovValidationInd: [null, [Validators.required]],
    tcpParamLktType: [null, [Validators.required, Validators.maxLength(30)]],
    transactionCode: []
  });

  constructor(
    protected transactionCodeParamsService: TransactionCodeParamsService,
    protected transactionCodesService: TransactionCodesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transactionCodeParams }) => {
      this.updateForm(transactionCodeParams);

      this.transactionCodesService.query().subscribe((res: HttpResponse<ITransactionCodes[]>) => (this.transactioncodes = res.body || []));
    });
  }

  updateForm(transactionCodeParams: ITransactionCodeParams): void {
    this.editForm.patchValue({
      id: transactionCodeParams.id,
      tcpTcdCode: transactionCodeParams.tcpTcdCode,
      tcpParamName: transactionCodeParams.tcpParamName,
      tcpParamDesc: transactionCodeParams.tcpParamDesc,
      tcpEnabledInd: transactionCodeParams.tcpEnabledInd,
      tcpParamDataTypeCd: transactionCodeParams.tcpParamDataTypeCd,
      tcpParamLength: transactionCodeParams.tcpParamLength,
      tcpDefaultValue: transactionCodeParams.tcpDefaultValue,
      tcpParamLovValidationInd: transactionCodeParams.tcpParamLovValidationInd,
      tcpParamLktType: transactionCodeParams.tcpParamLktType,
      transactionCode: transactionCodeParams.transactionCode
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transactionCodeParams = this.createFromForm();
    if (transactionCodeParams.id !== undefined) {
      this.subscribeToSaveResponse(this.transactionCodeParamsService.update(transactionCodeParams));
    } else {
      this.subscribeToSaveResponse(this.transactionCodeParamsService.create(transactionCodeParams));
    }
  }

  private createFromForm(): ITransactionCodeParams {
    return {
      ...new TransactionCodeParams(),
      id: this.editForm.get(['id'])!.value,
      tcpTcdCode: this.editForm.get(['tcpTcdCode'])!.value,
      tcpParamName: this.editForm.get(['tcpParamName'])!.value,
      tcpParamDesc: this.editForm.get(['tcpParamDesc'])!.value,
      tcpEnabledInd: this.editForm.get(['tcpEnabledInd'])!.value,
      tcpParamDataTypeCd: this.editForm.get(['tcpParamDataTypeCd'])!.value,
      tcpParamLength: this.editForm.get(['tcpParamLength'])!.value,
      tcpDefaultValue: this.editForm.get(['tcpDefaultValue'])!.value,
      tcpParamLovValidationInd: this.editForm.get(['tcpParamLovValidationInd'])!.value,
      tcpParamLktType: this.editForm.get(['tcpParamLktType'])!.value,
      transactionCode: this.editForm.get(['transactionCode'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransactionCodeParams>>): void {
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

  trackById(index: number, item: ITransactionCodes): any {
    return item.id;
  }
}
