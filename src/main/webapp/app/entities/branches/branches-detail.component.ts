import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBranches } from 'app/shared/model/branches.model';

@Component({
  selector: 'jhi-branches-detail',
  templateUrl: './branches-detail.component.html'
})
export class BranchesDetailComponent implements OnInit {
  branches: IBranches | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ branches }) => (this.branches = branches));
  }

  previousState(): void {
    window.history.back();
  }
}
