import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILookups } from 'app/shared/model/lookups.model';

@Component({
  selector: 'jhi-lookups-detail',
  templateUrl: './lookups-detail.component.html'
})
export class LookupsDetailComponent implements OnInit {
  lookups: ILookups | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lookups }) => (this.lookups = lookups));
  }

  previousState(): void {
    window.history.back();
  }
}
