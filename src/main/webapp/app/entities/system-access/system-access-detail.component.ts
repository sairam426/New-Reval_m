import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISystemAccess } from 'app/shared/model/system-access.model';

@Component({
  selector: 'jhi-system-access-detail',
  templateUrl: './system-access-detail.component.html'
})
export class SystemAccessDetailComponent implements OnInit {
  systemAccess: ISystemAccess | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ systemAccess }) => (this.systemAccess = systemAccess));
  }

  previousState(): void {
    window.history.back();
  }
}
