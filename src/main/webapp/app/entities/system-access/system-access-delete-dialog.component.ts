import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISystemAccess } from 'app/shared/model/system-access.model';
import { SystemAccessService } from './system-access.service';

@Component({
  templateUrl: './system-access-delete-dialog.component.html'
})
export class SystemAccessDeleteDialogComponent {
  systemAccess?: ISystemAccess;

  constructor(
    protected systemAccessService: SystemAccessService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.systemAccessService.delete(id).subscribe(() => {
      this.eventManager.broadcast('systemAccessListModification');
      this.activeModal.close();
    });
  }
}
