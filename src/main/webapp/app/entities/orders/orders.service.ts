import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrders } from 'app/shared/model/orders.model';

type EntityResponseType = HttpResponse<IOrders>;
type EntityArrayResponseType = HttpResponse<IOrders[]>;

@Injectable({ providedIn: 'root' })
export class OrdersService {
  public resourceUrl = SERVER_API_URL + 'api/orders';

  constructor(protected http: HttpClient) {}

  create(orders: IOrders): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orders);
    return this.http
      .post<IOrders>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(orders: IOrders): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orders);
    return this.http
      .put<IOrders>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOrders>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrders[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(orders: IOrders): IOrders {
    const copy: IOrders = Object.assign({}, orders, {
      ordOrderDt: orders.ordOrderDt && orders.ordOrderDt.isValid() ? orders.ordOrderDt.format(DATE_FORMAT) : undefined,
      ordRushRequestDueDt:
        orders.ordRushRequestDueDt && orders.ordRushRequestDueDt.isValid() ? orders.ordRushRequestDueDt.format(DATE_FORMAT) : undefined,
      ordOrgDuedate: orders.ordOrgDuedate && orders.ordOrgDuedate.isValid() ? orders.ordOrgDuedate.format(DATE_FORMAT) : undefined,
      ordProDuedate: orders.ordProDuedate && orders.ordProDuedate.isValid() ? orders.ordProDuedate.format(DATE_FORMAT) : undefined,
      ordUcdpFinalSubmissionDt:
        orders.ordUcdpFinalSubmissionDt && orders.ordUcdpFinalSubmissionDt.isValid()
          ? orders.ordUcdpFinalSubmissionDt.format(DATE_FORMAT)
          : undefined,
      ordLoanEstimatedCloseDt:
        orders.ordLoanEstimatedCloseDt && orders.ordLoanEstimatedCloseDt.isValid()
          ? orders.ordLoanEstimatedCloseDt.format(DATE_FORMAT)
          : undefined,
      ordLoanBorrowerRequestedCloseDt:
        orders.ordLoanBorrowerRequestedCloseDt && orders.ordLoanBorrowerRequestedCloseDt.isValid()
          ? orders.ordLoanBorrowerRequestedCloseDt.format(DATE_FORMAT)
          : undefined,
      ordRequestRrvendorAppraisalDraftRcvdDt:
        orders.ordRequestRrvendorAppraisalDraftRcvdDt && orders.ordRequestRrvendorAppraisalDraftRcvdDt.isValid()
          ? orders.ordRequestRrvendorAppraisalDraftRcvdDt.format(DATE_FORMAT)
          : undefined,
      ordValuationDueToClientDt:
        orders.ordValuationDueToClientDt && orders.ordValuationDueToClientDt.isValid()
          ? orders.ordValuationDueToClientDt.format(DATE_FORMAT)
          : undefined,
      ordValuationReportRecivedDt:
        orders.ordValuationReportRecivedDt && orders.ordValuationReportRecivedDt.isValid()
          ? orders.ordValuationReportRecivedDt.format(DATE_FORMAT)
          : undefined,
      ordValuationCompletionDt:
        orders.ordValuationCompletionDt && orders.ordValuationCompletionDt.isValid()
          ? orders.ordValuationCompletionDt.format(DATE_FORMAT)
          : undefined,
      ordReviewDt: orders.ordReviewDt && orders.ordReviewDt.isValid() ? orders.ordReviewDt.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ordOrderDt = res.body.ordOrderDt ? moment(res.body.ordOrderDt) : undefined;
      res.body.ordRushRequestDueDt = res.body.ordRushRequestDueDt ? moment(res.body.ordRushRequestDueDt) : undefined;
      res.body.ordOrgDuedate = res.body.ordOrgDuedate ? moment(res.body.ordOrgDuedate) : undefined;
      res.body.ordProDuedate = res.body.ordProDuedate ? moment(res.body.ordProDuedate) : undefined;
      res.body.ordUcdpFinalSubmissionDt = res.body.ordUcdpFinalSubmissionDt ? moment(res.body.ordUcdpFinalSubmissionDt) : undefined;
      res.body.ordLoanEstimatedCloseDt = res.body.ordLoanEstimatedCloseDt ? moment(res.body.ordLoanEstimatedCloseDt) : undefined;
      res.body.ordLoanBorrowerRequestedCloseDt = res.body.ordLoanBorrowerRequestedCloseDt
        ? moment(res.body.ordLoanBorrowerRequestedCloseDt)
        : undefined;
      res.body.ordRequestRrvendorAppraisalDraftRcvdDt = res.body.ordRequestRrvendorAppraisalDraftRcvdDt
        ? moment(res.body.ordRequestRrvendorAppraisalDraftRcvdDt)
        : undefined;
      res.body.ordValuationDueToClientDt = res.body.ordValuationDueToClientDt ? moment(res.body.ordValuationDueToClientDt) : undefined;
      res.body.ordValuationReportRecivedDt = res.body.ordValuationReportRecivedDt
        ? moment(res.body.ordValuationReportRecivedDt)
        : undefined;
      res.body.ordValuationCompletionDt = res.body.ordValuationCompletionDt ? moment(res.body.ordValuationCompletionDt) : undefined;
      res.body.ordReviewDt = res.body.ordReviewDt ? moment(res.body.ordReviewDt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((orders: IOrders) => {
        orders.ordOrderDt = orders.ordOrderDt ? moment(orders.ordOrderDt) : undefined;
        orders.ordRushRequestDueDt = orders.ordRushRequestDueDt ? moment(orders.ordRushRequestDueDt) : undefined;
        orders.ordOrgDuedate = orders.ordOrgDuedate ? moment(orders.ordOrgDuedate) : undefined;
        orders.ordProDuedate = orders.ordProDuedate ? moment(orders.ordProDuedate) : undefined;
        orders.ordUcdpFinalSubmissionDt = orders.ordUcdpFinalSubmissionDt ? moment(orders.ordUcdpFinalSubmissionDt) : undefined;
        orders.ordLoanEstimatedCloseDt = orders.ordLoanEstimatedCloseDt ? moment(orders.ordLoanEstimatedCloseDt) : undefined;
        orders.ordLoanBorrowerRequestedCloseDt = orders.ordLoanBorrowerRequestedCloseDt
          ? moment(orders.ordLoanBorrowerRequestedCloseDt)
          : undefined;
        orders.ordRequestRrvendorAppraisalDraftRcvdDt = orders.ordRequestRrvendorAppraisalDraftRcvdDt
          ? moment(orders.ordRequestRrvendorAppraisalDraftRcvdDt)
          : undefined;
        orders.ordValuationDueToClientDt = orders.ordValuationDueToClientDt ? moment(orders.ordValuationDueToClientDt) : undefined;
        orders.ordValuationReportRecivedDt = orders.ordValuationReportRecivedDt ? moment(orders.ordValuationReportRecivedDt) : undefined;
        orders.ordValuationCompletionDt = orders.ordValuationCompletionDt ? moment(orders.ordValuationCompletionDt) : undefined;
        orders.ordReviewDt = orders.ordReviewDt ? moment(orders.ordReviewDt) : undefined;
      });
    }
    return res;
  }
}
