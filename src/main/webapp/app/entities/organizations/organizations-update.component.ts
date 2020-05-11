import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrganizations, Organizations } from 'app/shared/model/organizations.model';
import { OrganizationsService } from './organizations.service';

@Component({
  selector: 'jhi-organizations-update',
  templateUrl: './organizations-update.component.html'
})
export class OrganizationsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    orgNbr: [null, [Validators.required, Validators.maxLength(30)]],
    orgName: [null, [Validators.required, Validators.maxLength(30)]],
    orgShortName: [null, [Validators.required, Validators.maxLength(30)]],
    orgAddress1: [],
    orgAddress2: [],
    orgCity: [],
    orgStateCd: [],
    orgZip: []
  });

  constructor(protected organizationsService: OrganizationsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organizations }) => {
      this.updateForm(organizations);
    });
  }

  updateForm(organizations: IOrganizations): void {
    this.editForm.patchValue({
      id: organizations.id,
      orgNbr: organizations.orgNbr,
      orgName: organizations.orgName,
      orgShortName: organizations.orgShortName,
      orgAddress1: organizations.orgAddress1,
      orgAddress2: organizations.orgAddress2,
      orgCity: organizations.orgCity,
      orgStateCd: organizations.orgStateCd,
      orgZip: organizations.orgZip
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const organizations = this.createFromForm();
    if (organizations.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationsService.update(organizations));
    } else {
      this.subscribeToSaveResponse(this.organizationsService.create(organizations));
    }
  }

  private createFromForm(): IOrganizations {
    return {
      ...new Organizations(),
      id: this.editForm.get(['id'])!.value,
      orgNbr: this.editForm.get(['orgNbr'])!.value,
      orgName: this.editForm.get(['orgName'])!.value,
      orgShortName: this.editForm.get(['orgShortName'])!.value,
      orgAddress1: this.editForm.get(['orgAddress1'])!.value,
      orgAddress2: this.editForm.get(['orgAddress2'])!.value,
      orgCity: this.editForm.get(['orgCity'])!.value,
      orgStateCd: this.editForm.get(['orgStateCd'])!.value,
      orgZip: this.editForm.get(['orgZip'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizations>>): void {
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
