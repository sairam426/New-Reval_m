import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderComments } from 'app/shared/model/order-comments.model';
import { OrderCommentsService } from './order-comments.service';

@Component({
  templateUrl: './order-comments-delete-dialog.component.html'
})
export class OrderCommentsDeleteDialogComponent {
  orderComments?: IOrderComments;

  constructor(
    protected orderCommentsService: OrderCommentsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.orderCommentsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('orderCommentsListModification');
      this.activeModal.close();
    });
  }
}
