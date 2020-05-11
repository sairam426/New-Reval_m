import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrderComments } from 'app/shared/model/order-comments.model';
import { OrderCommentsService } from './order-comments.service';
import { OrderCommentsDeleteDialogComponent } from './order-comments-delete-dialog.component';

@Component({
  selector: 'jhi-order-comments',
  templateUrl: './order-comments.component.html'
})
export class OrderCommentsComponent implements OnInit, OnDestroy {
  orderComments?: IOrderComments[];
  eventSubscriber?: Subscription;

  constructor(
    protected orderCommentsService: OrderCommentsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.orderCommentsService.query().subscribe((res: HttpResponse<IOrderComments[]>) => (this.orderComments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrderComments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrderComments): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrderComments(): void {
    this.eventSubscriber = this.eventManager.subscribe('orderCommentsListModification', () => this.loadAll());
  }

  delete(orderComments: IOrderComments): void {
    const modalRef = this.modalService.open(OrderCommentsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.orderComments = orderComments;
  }
}
