import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderDocuments } from 'app/shared/model/order-documents.model';

type EntityResponseType = HttpResponse<IOrderDocuments>;
type EntityArrayResponseType = HttpResponse<IOrderDocuments[]>;

@Injectable({ providedIn: 'root' })
export class OrderDocumentsService {
  public resourceUrl = SERVER_API_URL + 'api/order-documents';

  constructor(protected http: HttpClient) {}

  create(orderDocuments: IOrderDocuments): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderDocuments);
    return this.http
      .post<IOrderDocuments>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(orderDocuments: IOrderDocuments): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderDocuments);
    return this.http
      .put<IOrderDocuments>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOrderDocuments>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrderDocuments[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(orderDocuments: IOrderDocuments): IOrderDocuments {
    const copy: IOrderDocuments = Object.assign({}, orderDocuments, {
      odoDocumentDueDt:
        orderDocuments.odoDocumentDueDt && orderDocuments.odoDocumentDueDt.isValid()
          ? orderDocuments.odoDocumentDueDt.format(DATE_FORMAT)
          : undefined,
      odoDocumentRcvdDt:
        orderDocuments.odoDocumentRcvdDt && orderDocuments.odoDocumentRcvdDt.isValid()
          ? orderDocuments.odoDocumentRcvdDt.format(DATE_FORMAT)
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.odoDocumentDueDt = res.body.odoDocumentDueDt ? moment(res.body.odoDocumentDueDt) : undefined;
      res.body.odoDocumentRcvdDt = res.body.odoDocumentRcvdDt ? moment(res.body.odoDocumentRcvdDt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((orderDocuments: IOrderDocuments) => {
        orderDocuments.odoDocumentDueDt = orderDocuments.odoDocumentDueDt ? moment(orderDocuments.odoDocumentDueDt) : undefined;
        orderDocuments.odoDocumentRcvdDt = orderDocuments.odoDocumentRcvdDt ? moment(orderDocuments.odoDocumentRcvdDt) : undefined;
      });
    }
    return res;
  }
}
