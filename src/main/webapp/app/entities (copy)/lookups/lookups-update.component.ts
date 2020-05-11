import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILookups, Lookups } from 'app/shared/model/lookups.model';
import { LookupsService } from './lookups.service';
import { ILookupTypes } from 'app/shared/model/lookup-types.model';
import { LookupTypesService } from 'app/entities/lookup-types/lookup-types.service';

@Component({
  selector: 'jhi-lookups-update',
  templateUrl: './lookups-update.component.html'
})
export class LookupsUpdateComponent implements OnInit {
  isSaving = false;
  lookuptypes: ILookupTypes[] = [];

  editForm = this.fb.group({
    id: [],
    lkcCode: [null, [Validators.required, Validators.maxLength(30)]],
    lkcSubCode: [null, [Validators.required, Validators.maxLength(30)]],
    lkcSort: [null, [Validators.required, Validators.maxLength(30)]],
    lkcEnabledInd: [null, [Validators.required]],
    lkcDesc: [null, [Validators.maxLength(80)]],
    lookUpType: []
  });

  constructor(
    protected lookupsService: LookupsService,
    protected lookupTypesService: LookupTypesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lookups }) => {
      this.updateForm(lookups);

      this.lookupTypesService.query().subscribe((res: HttpResponse<ILookupTypes[]>) => (this.lookuptypes = res.body || []));
    });
  }

  updateForm(lookups: ILookups): void {
    this.editForm.patchValue({
      id: lookups.id,
      lkcCode: lookups.lkcCode,
      lkcSubCode: lookups.lkcSubCode,
      lkcSort: lookups.lkcSort,
      lkcEnabledInd: lookups.lkcEnabledInd,
      lkcDesc: lookups.lkcDesc,
      lookUpType: lookups.lookUpType
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lookups = this.createFromForm();
    if (lookups.id !== undefined) {
      this.subscribeToSaveResponse(this.lookupsService.update(lookups));
    } else {
      this.subscribeToSaveResponse(this.lookupsService.create(lookups));
    }
  }

  private createFromForm(): ILookups {
    return {
      ...new Lookups(),
      id: this.editForm.get(['id'])!.value,
      lkcCode: this.editForm.get(['lkcCode'])!.value,
      lkcSubCode: this.editForm.get(['lkcSubCode'])!.value,
      lkcSort: this.editForm.get(['lkcSort'])!.value,
      lkcEnabledInd: this.editForm.get(['lkcEnabledInd'])!.value,
      lkcDesc: this.editForm.get(['lkcDesc'])!.value,
      lookUpType: this.editForm.get(['lookUpType'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILookups>>): void {
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

  trackById(index: number, item: ILookupTypes): any {
    return item.id;
  }
}
