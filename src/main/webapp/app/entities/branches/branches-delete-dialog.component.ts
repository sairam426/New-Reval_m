import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBranches } from 'app/shared/model/branches.model';
import { BranchesService } from './branches.service';

@Component({
  templateUrl: './branches-delete-dialog.component.html'
})
export class BranchesDeleteDialogComponent {
  branches?: IBranches;

  constructor(protected branchesService: BranchesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.branchesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('branchesListModification');
      this.activeModal.close();
    });
  }
}
