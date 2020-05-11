import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderComments } from 'app/shared/model/order-comments.model';

@Component({
  selector: 'jhi-order-comments-detail',
  templateUrl: './order-comments-detail.component.html'
})
export class OrderCommentsDetailComponent implements OnInit {
  orderComments: IOrderComments | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderComments }) => (this.orderComments = orderComments));
  }

  previousState(): void {
    window.history.back();
  }
}
