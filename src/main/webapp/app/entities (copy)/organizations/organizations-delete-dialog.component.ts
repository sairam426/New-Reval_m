import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganizations } from 'app/shared/model/organizations.model';
import { OrganizationsService } from './organizations.service';

@Component({
  templateUrl: './organizations-delete-dialog.component.html'
})
export class OrganizationsDeleteDialogComponent {
  organizations?: IOrganizations;

  constructor(
    protected organizationsService: OrganizationsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.organizationsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('organizationsListModification');
      this.activeModal.close();
    });
  }
}
