import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderComments } from 'app/shared/model/order-comments.model';

type EntityResponseType = HttpResponse<IOrderComments>;
type EntityArrayResponseType = HttpResponse<IOrderComments[]>;

@Injectable({ providedIn: 'root' })
export class OrderCommentsService {
  public resourceUrl = SERVER_API_URL + 'api/order-comments';

  constructor(protected http: HttpClient) {}

  create(orderComments: IOrderComments): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderComments);
    return this.http
      .post<IOrderComments>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(orderComments: IOrderComments): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderComments);
    return this.http
      .put<IOrderComments>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOrderComments>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrderComments[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(orderComments: IOrderComments): IOrderComments {
    const copy: IOrderComments = Object.assign({}, orderComments, {
      ocmCommentDate:
        orderComments.ocmCommentDate && orderComments.ocmCommentDate.isValid()
          ? orderComments.ocmCommentDate.format(DATE_FORMAT)
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ocmCommentDate = res.body.ocmCommentDate ? moment(res.body.ocmCommentDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((orderComments: IOrderComments) => {
        orderComments.ocmCommentDate = orderComments.ocmCommentDate ? moment(orderComments.ocmCommentDate) : undefined;
      });
    }
    return res;
  }
}
