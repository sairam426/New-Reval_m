import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProviders, Providers } from 'app/shared/model/providers.model';
import { ProvidersService } from './providers.service';

@Component({
  selector: 'jhi-providers-update',
  templateUrl: './providers-update.component.html'
})
export class ProvidersUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    proNbr: [null, [Validators.required, Validators.maxLength(30)]],
    proName: [null, [Validators.required, Validators.maxLength(30)]],
    proShortName: [null, [Validators.required, Validators.maxLength(80)]],
    proStatusCd: [null, [Validators.required, Validators.maxLength(30)]],
    proTypeCd: [],
    proGroupCd: [],
    proLicenseNbr: [],
    proLicenseStatusCd: [],
    proAddress1: [],
    proAddress2: [],
    proCity: [],
    proStateCd: [],
    proZip: []
  });

  constructor(protected providersService: ProvidersService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ providers }) => {
      this.updateForm(providers);
    });
  }

  updateForm(providers: IProviders): void {
    this.editForm.patchValue({
      id: providers.id,
      proNbr: providers.proNbr,
      proName: providers.proName,
      proShortName: providers.proShortName,
      proStatusCd: providers.proStatusCd,
      proTypeCd: providers.proTypeCd,
      proGroupCd: providers.proGroupCd,
      proLicenseNbr: providers.proLicenseNbr,
      proLicenseStatusCd: providers.proLicenseStatusCd,
      proAddress1: providers.proAddress1,
      proAddress2: providers.proAddress2,
      proCity: providers.proCity,
      proStateCd: providers.proStateCd,
      proZip: providers.proZip
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const providers = this.createFromForm();
    if (providers.id !== undefined) {
      this.subscribeToSaveResponse(this.providersService.update(providers));
    } else {
      this.subscribeToSaveResponse(this.providersService.create(providers));
    }
  }

  private createFromForm(): IProviders {
    return {
      ...new Providers(),
      id: this.editForm.get(['id'])!.value,
      proNbr: this.editForm.get(['proNbr'])!.value,
      proName: this.editForm.get(['proName'])!.value,
      proShortName: this.editForm.get(['proShortName'])!.value,
      proStatusCd: this.editForm.get(['proStatusCd'])!.value,
      proTypeCd: this.editForm.get(['proTypeCd'])!.value,
      proGroupCd: this.editForm.get(['proGroupCd'])!.value,
      proLicenseNbr: this.editForm.get(['proLicenseNbr'])!.value,
      proLicenseStatusCd: this.editForm.get(['proLicenseStatusCd'])!.value,
      proAddress1: this.editForm.get(['proAddress1'])!.value,
      proAddress2: this.editForm.get(['proAddress2'])!.value,
      proCity: this.editForm.get(['proCity'])!.value,
      proStateCd: this.editForm.get(['proStateCd'])!.value,
      proZip: this.editForm.get(['proZip'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProviders>>): void {
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
