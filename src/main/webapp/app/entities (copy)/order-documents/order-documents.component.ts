import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrderDocuments } from 'app/shared/model/order-documents.model';
import { OrderDocumentsService } from './order-documents.service';
import { OrderDocumentsDeleteDialogComponent } from './order-documents-delete-dialog.component';

@Component({
  selector: 'jhi-order-documents',
  templateUrl: './order-documents.component.html'
})
export class OrderDocumentsComponent implements OnInit, OnDestroy {
  orderDocuments?: IOrderDocuments[];
  eventSubscriber?: Subscription;

  constructor(
    protected orderDocumentsService: OrderDocumentsService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.orderDocumentsService.query().subscribe((res: HttpResponse<IOrderDocuments[]>) => (this.orderDocuments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrderDocuments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrderDocuments): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInOrderDocuments(): void {
    this.eventSubscriber = this.eventManager.subscribe('orderDocumentsListModification', () => this.loadAll());
  }

  delete(orderDocuments: IOrderDocuments): void {
    const modalRef = this.modalService.open(OrderDocumentsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.orderDocuments = orderDocuments;
  }
}
