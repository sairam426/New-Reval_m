import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBranches, Branches } from 'app/shared/model/branches.model';
import { BranchesService } from './branches.service';
import { ICompanies } from 'app/shared/model/companies.model';
import { CompaniesService } from 'app/entities/companies/companies.service';

@Component({
  selector: 'jhi-branches-update',
  templateUrl: './branches-update.component.html'
})
export class BranchesUpdateComponent implements OnInit {
  isSaving = false;
  companies: ICompanies[] = [];

  editForm = this.fb.group({
    id: [],
    brnNbr: [null, [Validators.required, Validators.maxLength(30)]],
    brnName: [null, [Validators.required, Validators.maxLength(80)]],
    brnCode: [null, [Validators.required, Validators.maxLength(30)]],
    brnShortName: [null, [Validators.required, Validators.maxLength(30)]],
    brnAddress1: [null, [Validators.required, Validators.maxLength(80)]],
    brnAddress2: [null, [Validators.required, Validators.maxLength(80)]],
    brnCity: [null, [Validators.required, Validators.maxLength(80)]],
    brnStateCd: [null, [Validators.required, Validators.maxLength(30)]],
    brnZip: [null, [Validators.required, Validators.maxLength(30)]],
    brnEnabledInd: [null, [Validators.required]],
    brnRegioinCd: [null, [Validators.required, Validators.maxLength(30)]],
    brnServiceTypeCd: [null, [Validators.maxLength(30)]],
    company: []
  });

  constructor(
    protected branchesService: BranchesService,
    protected companiesService: CompaniesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ branches }) => {
      this.updateForm(branches);

      this.companiesService.query().subscribe((res: HttpResponse<ICompanies[]>) => (this.companies = res.body || []));
    });
  }

  updateForm(branches: IBranches): void {
    this.editForm.patchValue({
      id: branches.id,
      brnNbr: branches.brnNbr,
      brnName: branches.brnName,
      brnCode: branches.brnCode,
      brnShortName: branches.brnShortName,
      brnAddress1: branches.brnAddress1,
      brnAddress2: branches.brnAddress2,
      brnCity: branches.brnCity,
      brnStateCd: branches.brnStateCd,
      brnZip: branches.brnZip,
      brnEnabledInd: branches.brnEnabledInd,
      brnRegioinCd: branches.brnRegioinCd,
      brnServiceTypeCd: branches.brnServiceTypeCd,
      company: branches.company
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const branches = this.createFromForm();
    if (branches.id !== undefined) {
      this.subscribeToSaveResponse(this.branchesService.update(branches));
    } else {
      this.subscribeToSaveResponse(this.branchesService.create(branches));
    }
  }

  private createFromForm(): IBranches {
    return {
      ...new Branches(),
      id: this.editForm.get(['id'])!.value,
      brnNbr: this.editForm.get(['brnNbr'])!.value,
      brnName: this.editForm.get(['brnName'])!.value,
      brnCode: this.editForm.get(['brnCode'])!.value,
      brnShortName: this.editForm.get(['brnShortName'])!.value,
      brnAddress1: this.editForm.get(['brnAddress1'])!.value,
      brnAddress2: this.editForm.get(['brnAddress2'])!.value,
      brnCity: this.editForm.get(['brnCity'])!.value,
      brnStateCd: this.editForm.get(['brnStateCd'])!.value,
      brnZip: this.editForm.get(['brnZip'])!.value,
      brnEnabledInd: this.editForm.get(['brnEnabledInd'])!.value,
      brnRegioinCd: this.editForm.get(['brnRegioinCd'])!.value,
      brnServiceTypeCd: this.editForm.get(['brnServiceTypeCd'])!.value,
      company: this.editForm.get(['company'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBranches>>): void {
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

  trackById(index: number, item: ICompanies): any {
    return item.id;
  }
}
