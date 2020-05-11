import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISystemAccess, SystemAccess } from 'app/shared/model/system-access.model';
import { SystemAccessService } from './system-access.service';

@Component({
  selector: 'jhi-system-access-update',
  templateUrl: './system-access-update.component.html'
})
export class SystemAccessUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    sacAccessKey: [null, [Validators.required, Validators.maxLength(240)]],
    sacAccessTypeCd: [null, [Validators.required, Validators.maxLength(30)]],
    sacAccessValue: [null, [Validators.required, Validators.maxLength(80)]],
    sacAllowedInd: [null, [Validators.required]]
  });

  constructor(protected systemAccessService: SystemAccessService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ systemAccess }) => {
      this.updateForm(systemAccess);
    });
  }

  updateForm(systemAccess: ISystemAccess): void {
    this.editForm.patchValue({
      id: systemAccess.id,
      sacAccessKey: systemAccess.sacAccessKey,
      sacAccessTypeCd: systemAccess.sacAccessTypeCd,
      sacAccessValue: systemAccess.sacAccessValue,
      sacAllowedInd: systemAccess.sacAllowedInd
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const systemAccess = this.createFromForm();
    if (systemAccess.id !== undefined) {
      this.subscribeToSaveResponse(this.systemAccessService.update(systemAccess));
    } else {
      this.subscribeToSaveResponse(this.systemAccessService.create(systemAccess));
    }
  }

  private createFromForm(): ISystemAccess {
    return {
      ...new SystemAccess(),
      id: this.editForm.get(['id'])!.value,
      sacAccessKey: this.editForm.get(['sacAccessKey'])!.value,
      sacAccessTypeCd: this.editForm.get(['sacAccessTypeCd'])!.value,
      sacAccessValue: this.editForm.get(['sacAccessValue'])!.value,
      sacAllowedInd: this.editForm.get(['sacAllowedInd'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISystemAccess>>): void {
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
