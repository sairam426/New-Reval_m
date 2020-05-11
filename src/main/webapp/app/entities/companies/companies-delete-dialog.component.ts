import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompanies } from 'app/shared/model/companies.model';
import { CompaniesService } from './companies.service';

@Component({
  templateUrl: './companies-delete-dialog.component.html'
})
export class CompaniesDeleteDialogComponent {
  companies?: ICompanies;

  constructor(protected companiesService: CompaniesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.companiesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('companiesListModification');
      this.activeModal.close();
    });
  }
}
