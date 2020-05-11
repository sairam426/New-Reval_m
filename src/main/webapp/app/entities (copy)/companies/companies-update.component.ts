import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICompanies, Companies } from 'app/shared/model/companies.model';
import { CompaniesService } from './companies.service';

@Component({
  selector: 'jhi-companies-update',
  templateUrl: './companies-update.component.html'
})
export class CompaniesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    cmpNbr: [null, [Validators.required, Validators.maxLength(30)]],
    cmpName: [null, [Validators.required, Validators.maxLength(80)]],
    cmpServiceTypeCd: [null, [Validators.maxLength(30)]],
    cmpShortName: [null, [Validators.required, Validators.maxLength(30)]],
    cmpStatusCd: [null, [Validators.required, Validators.maxLength(30)]],
    cmpTypeCd: [null, [Validators.required, Validators.maxLength(30)]],
    cmpAddress1: [null, [Validators.required, Validators.maxLength(80)]],
    cmpAddress2: [null, [Validators.required, Validators.maxLength(80)]],
    cmpCity: [null, [Validators.required, Validators.maxLength(30)]],
    cmpStateCd: [null, [Validators.required, Validators.maxLength(30)]],
    cmpZip: [null, [Validators.required, Validators.maxLength(30)]],
    cmpEnabledInd: [null, [Validators.required]]
  });

  constructor(protected companiesService: CompaniesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ companies }) => {
      this.updateForm(companies);
    });
  }

  updateForm(companies: ICompanies): void {
    this.editForm.patchValue({
      id: companies.id,
      cmpNbr: companies.cmpNbr,
      cmpName: companies.cmpName,
      cmpServiceTypeCd: companies.cmpServiceTypeCd,
      cmpShortName: companies.cmpShortName,
      cmpStatusCd: companies.cmpStatusCd,
      cmpTypeCd: companies.cmpTypeCd,
      cmpAddress1: companies.cmpAddress1,
      cmpAddress2: companies.cmpAddress2,
      cmpCity: companies.cmpCity,
      cmpStateCd: companies.cmpStateCd,
      cmpZip: companies.cmpZip,
      cmpEnabledInd: companies.cmpEnabledInd
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const companies = this.createFromForm();
    if (companies.id !== undefined) {
      this.subscribeToSaveResponse(this.companiesService.update(companies));
    } else {
      this.subscribeToSaveResponse(this.companiesService.create(companies));
    }
  }

  private createFromForm(): ICompanies {
    return {
      ...new Companies(),
      id: this.editForm.get(['id'])!.value,
      cmpNbr: this.editForm.get(['cmpNbr'])!.value,
      cmpName: this.editForm.get(['cmpName'])!.value,
      cmpServiceTypeCd: this.editForm.get(['cmpServiceTypeCd'])!.value,
      cmpShortName: this.editForm.get(['cmpShortName'])!.value,
      cmpStatusCd: this.editForm.get(['cmpStatusCd'])!.value,
      cmpTypeCd: this.editForm.get(['cmpTypeCd'])!.value,
      cmpAddress1: this.editForm.get(['cmpAddress1'])!.value,
      cmpAddress2: this.editForm.get(['cmpAddress2'])!.value,
      cmpCity: this.editForm.get(['cmpCity'])!.value,
      cmpStateCd: this.editForm.get(['cmpStateCd'])!.value,
      cmpZip: this.editForm.get(['cmpZip'])!.value,
      cmpEnabledInd: this.editForm.get(['cmpEnabledInd'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompanies>>): void {
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
