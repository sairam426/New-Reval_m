import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILookupTypes } from 'app/shared/model/lookup-types.model';
import { LookupTypesService } from './lookup-types.service';

@Component({
  templateUrl: './lookup-types-delete-dialog.component.html'
})
export class LookupTypesDeleteDialogComponent {
  lookupTypes?: ILookupTypes;

  constructor(
    protected lookupTypesService: LookupTypesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lookupTypesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('lookupTypesListModification');
      this.activeModal.close();
    });
  }
}
