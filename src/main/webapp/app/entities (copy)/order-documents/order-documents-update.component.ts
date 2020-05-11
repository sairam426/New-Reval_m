import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IOrderDocuments, OrderDocuments } from 'app/shared/model/order-documents.model';
import { OrderDocumentsService } from './order-documents.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders/orders.service';

@Component({
  selector: 'jhi-order-documents-update',
  templateUrl: './order-documents-update.component.html'
})
export class OrderDocumentsUpdateComponent implements OnInit {
  isSaving = false;
  orders: IOrders[] = [];
  odoDocumentDueDtDp: any;
  odoDocumentRcvdDtDp: any;

  editForm = this.fb.group({
    id: [],
    odoDocumentMimeTypeCd: [null, [Validators.required, Validators.maxLength(30)]],
    odoDocumentTypeCd: [null, [Validators.required, Validators.maxLength(30)]],
    odoExternalStorageLink: [null, [Validators.maxLength(240)]],
    odoExternalStorageFileName: [null, [Validators.maxLength(240)]],
    odoDocumentDueToCd: [],
    odoDocumentDueFromCd: [],
    odoDocumentDueDt: [],
    odoDocumentRcvdDt: [],
    odoDocumentStatusCd: [null, [Validators.required, Validators.maxLength(30)]],
    odoDocument: [],
    odoDocumentContentType: [],
    order: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected orderDocumentsService: OrderDocumentsService,
    protected ordersService: OrdersService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderDocuments }) => {
      this.updateForm(orderDocuments);

      this.ordersService.query().subscribe((res: HttpResponse<IOrders[]>) => (this.orders = res.body || []));
    });
  }

  updateForm(orderDocuments: IOrderDocuments): void {
    this.editForm.patchValue({
      id: orderDocuments.id,
      odoDocumentMimeTypeCd: orderDocuments.odoDocumentMimeTypeCd,
      odoDocumentTypeCd: orderDocuments.odoDocumentTypeCd,
      odoExternalStorageLink: orderDocuments.odoExternalStorageLink,
      odoExternalStorageFileName: orderDocuments.odoExternalStorageFileName,
      odoDocumentDueToCd: orderDocuments.odoDocumentDueToCd,
      odoDocumentDueFromCd: orderDocuments.odoDocumentDueFromCd,
      odoDocumentDueDt: orderDocuments.odoDocumentDueDt,
      odoDocumentRcvdDt: orderDocuments.odoDocumentRcvdDt,
      odoDocumentStatusCd: orderDocuments.odoDocumentStatusCd,
      odoDocument: orderDocuments.odoDocument,
      odoDocumentContentType: orderDocuments.odoDocumentContentType,
      order: orderDocuments.order
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('revalApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderDocuments = this.createFromForm();
    if (orderDocuments.id !== undefined) {
      this.subscribeToSaveResponse(this.orderDocumentsService.update(orderDocuments));
    } else {
      this.subscribeToSaveResponse(this.orderDocumentsService.create(orderDocuments));
    }
  }

  private createFromForm(): IOrderDocuments {
    return {
      ...new OrderDocuments(),
      id: this.editForm.get(['id'])!.value,
      odoDocumentMimeTypeCd: this.editForm.get(['odoDocumentMimeTypeCd'])!.value,
      odoDocumentTypeCd: this.editForm.get(['odoDocumentTypeCd'])!.value,
      odoExternalStorageLink: this.editForm.get(['odoExternalStorageLink'])!.value,
      odoExternalStorageFileName: this.editForm.get(['odoExternalStorageFileName'])!.value,
      odoDocumentDueToCd: this.editForm.get(['odoDocumentDueToCd'])!.value,
      odoDocumentDueFromCd: this.editForm.get(['odoDocumentDueFromCd'])!.value,
      odoDocumentDueDt: this.editForm.get(['odoDocumentDueDt'])!.value,
      odoDocumentRcvdDt: this.editForm.get(['odoDocumentRcvdDt'])!.value,
      odoDocumentStatusCd: this.editForm.get(['odoDocumentStatusCd'])!.value,
      odoDocumentContentType: this.editForm.get(['odoDocumentContentType'])!.value,
      odoDocument: this.editForm.get(['odoDocument'])!.value,
      order: this.editForm.get(['order'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderDocuments>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IOrders): any {
    return item.id;
  }
}
