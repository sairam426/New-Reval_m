import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITransactionCodes } from 'app/shared/model/transaction-codes.model';

type EntityResponseType = HttpResponse<ITransactionCodes>;
type EntityArrayResponseType = HttpResponse<ITransactionCodes[]>;

@Injectable({ providedIn: 'root' })
export class TransactionCodesService {
  public resourceUrl = SERVER_API_URL + 'api/transaction-codes';

  constructor(protected http: HttpClient) {}

  create(transactionCodes: ITransactionCodes): Observable<EntityResponseType> {
    return this.http.post<ITransactionCodes>(this.resourceUrl, transactionCodes, { observe: 'response' });
  }

  update(transactionCodes: ITransactionCodes): Observable<EntityResponseType> {
    return this.http.put<ITransactionCodes>(this.resourceUrl, transactionCodes, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITransactionCodes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITransactionCodes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
