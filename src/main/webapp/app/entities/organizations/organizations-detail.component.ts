import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganizations } from 'app/shared/model/organizations.model';

@Component({
  selector: 'jhi-organizations-detail',
  templateUrl: './organizations-detail.component.html'
})
export class OrganizationsDetailComponent implements OnInit {
  organizations: IOrganizations | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organizations }) => (this.organizations = organizations));
  }

  previousState(): void {
    window.history.back();
  }
}
