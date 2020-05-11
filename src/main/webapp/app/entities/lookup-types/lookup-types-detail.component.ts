import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILookupTypes } from 'app/shared/model/lookup-types.model';

@Component({
  selector: 'jhi-lookup-types-detail',
  templateUrl: './lookup-types-detail.component.html'
})
export class LookupTypesDetailComponent implements OnInit {
  lookupTypes: ILookupTypes | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lookupTypes }) => (this.lookupTypes = lookupTypes));
  }

  previousState(): void {
    window.history.back();
  }
}
