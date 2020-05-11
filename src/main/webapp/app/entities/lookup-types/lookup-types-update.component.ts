import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILookupTypes, LookupTypes } from 'app/shared/model/lookup-types.model';
import { LookupTypesService } from './lookup-types.service';

@Component({
  selector: 'jhi-lookup-types-update',
  templateUrl: './lookup-types-update.component.html'
})
export class LookupTypesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    lktType: [null, [Validators.required, Validators.maxLength(30)]],
    lktDesc: [null, [Validators.required, Validators.maxLength(80)]]
  });

  constructor(protected lookupTypesService: LookupTypesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lookupTypes }) => {
      this.updateForm(lookupTypes);
    });
  }

  updateForm(lookupTypes: ILookupTypes): void {
    this.editForm.patchValue({
      id: lookupTypes.id,
      lktType: lookupTypes.lktType,
      lktDesc: lookupTypes.lktDesc
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lookupTypes = this.createFromForm();
    if (lookupTypes.id !== undefined) {
      this.subscribeToSaveResponse(this.lookupTypesService.update(lookupTypes));
    } else {
      this.subscribeToSaveResponse(this.lookupTypesService.create(lookupTypes));
    }
  }

  private createFromForm(): ILookupTypes {
    return {
      ...new LookupTypes(),
      id: this.editForm.get(['id'])!.value,
      lktType: this.editForm.get(['lktType'])!.value,
      lktDesc: this.editForm.get(['lktDesc'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILookupTypes>>): void {
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
