import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrderComments, OrderComments } from 'app/shared/model/order-comments.model';
import { OrderCommentsService } from './order-comments.service';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders/orders.service';

@Component({
  selector: 'jhi-order-comments-update',
  templateUrl: './order-comments-update.component.html'
})
export class OrderCommentsUpdateComponent implements OnInit {
  isSaving = false;
  orders: IOrders[] = [];
  ocmCommentDateDp: any;

  editForm = this.fb.group({
    id: [],
    ocmCommentTypeCd: [null, [Validators.required, Validators.maxLength(30)]],
    ocmCommentSubTypeCd: [null, [Validators.required, Validators.maxLength(30)]],
    ocmCommentBy: [null, [Validators.required, Validators.maxLength(30)]],
    ocmCommentDate: [null, [Validators.required]],
    ocmCommentImpInd: [null, [Validators.required]],
    ocmComment: [null, [Validators.required, Validators.maxLength(240)]],
    order: []
  });

  constructor(
    protected orderCommentsService: OrderCommentsService,
    protected ordersService: OrdersService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderComments }) => {
      this.updateForm(orderComments);

      this.ordersService.query().subscribe((res: HttpResponse<IOrders[]>) => (this.orders = res.body || []));
    });
  }

  updateForm(orderComments: IOrderComments): void {
    this.editForm.patchValue({
      id: orderComments.id,
      ocmCommentTypeCd: orderComments.ocmCommentTypeCd,
      ocmCommentSubTypeCd: orderComments.ocmCommentSubTypeCd,
      ocmCommentBy: orderComments.ocmCommentBy,
      ocmCommentDate: orderComments.ocmCommentDate,
      ocmCommentImpInd: orderComments.ocmCommentImpInd,
      ocmComment: orderComments.ocmComment,
      order: orderComments.order
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderComments = this.createFromForm();
    if (orderComments.id !== undefined) {
      this.subscribeToSaveResponse(this.orderCommentsService.update(orderComments));
    } else {
      this.subscribeToSaveResponse(this.orderCommentsService.create(orderComments));
    }
  }

  private createFromForm(): IOrderComments {
    return {
      ...new OrderComments(),
      id: this.editForm.get(['id'])!.value,
      ocmCommentTypeCd: this.editForm.get(['ocmCommentTypeCd'])!.value,
      ocmCommentSubTypeCd: this.editForm.get(['ocmCommentSubTypeCd'])!.value,
      ocmCommentBy: this.editForm.get(['ocmCommentBy'])!.value,
      ocmCommentDate: this.editForm.get(['ocmCommentDate'])!.value,
      ocmCommentImpInd: this.editForm.get(['ocmCommentImpInd'])!.value,
      ocmComment: this.editForm.get(['ocmComment'])!.value,
      order: this.editForm.get(['order'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderComments>>): void {
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
