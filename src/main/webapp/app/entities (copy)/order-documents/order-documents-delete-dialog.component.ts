import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderDocuments } from 'app/shared/model/order-documents.model';
import { OrderDocumentsService } from './order-documents.service';

@Component({
  templateUrl: './order-documents-delete-dialog.component.html'
})
export class OrderDocumentsDeleteDialogComponent {
  orderDocuments?: IOrderDocuments;

  constructor(
    protected orderDocumentsService: OrderDocumentsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.orderDocumentsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('orderDocumentsListModification');
      this.activeModal.close();
    });
  }
}
