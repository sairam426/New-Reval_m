import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProviders } from 'app/shared/model/providers.model';
import { ProvidersService } from './providers.service';

@Component({
  templateUrl: './providers-delete-dialog.component.html'
})
export class ProvidersDeleteDialogComponent {
  providers?: IProviders;

  constructor(protected providersService: ProvidersService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.providersService.delete(id).subscribe(() => {
      this.eventManager.broadcast('providersListModification');
      this.activeModal.close();
    });
  }
}
