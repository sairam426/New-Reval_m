import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILookups } from 'app/shared/model/lookups.model';
import { LookupsService } from './lookups.service';

@Component({
  templateUrl: './lookups-delete-dialog.component.html'
})
export class LookupsDeleteDialogComponent {
  lookups?: ILookups;

  constructor(protected lookupsService: LookupsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lookupsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('lookupsListModification');
      this.activeModal.close();
    });
  }
}
