import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProviders } from 'app/shared/model/providers.model';

@Component({
  selector: 'jhi-providers-detail',
  templateUrl: './providers-detail.component.html'
})
export class ProvidersDetailComponent implements OnInit {
  providers: IProviders | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ providers }) => (this.providers = providers));
  }

  previousState(): void {
    window.history.back();
  }
}
