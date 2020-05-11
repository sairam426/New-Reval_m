import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IOrderDocuments } from 'app/shared/model/order-documents.model';

@Component({
  selector: 'jhi-order-documents-detail',
  templateUrl: './order-documents-detail.component.html'
})
export class OrderDocumentsDetailComponent implements OnInit {
  orderDocuments: IOrderDocuments | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderDocuments }) => (this.orderDocuments = orderDocuments));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
