import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITransactionCodeParams } from 'app/shared/model/transaction-code-params.model';

type EntityResponseType = HttpResponse<ITransactionCodeParams>;
type EntityArrayResponseType = HttpResponse<ITransactionCodeParams[]>;

@Injectable({ providedIn: 'root' })
export class TransactionCodeParamsService {
  public resourceUrl = SERVER_API_URL + 'api/transaction-code-params';

  constructor(protected http: HttpClient) {}

  create(transactionCodeParams: ITransactionCodeParams): Observable<EntityResponseType> {
    return this.http.post<ITransactionCodeParams>(this.resourceUrl, transactionCodeParams, { observe: 'response' });
  }

  update(transactionCodeParams: ITransactionCodeParams): Observable<EntityResponseType> {
    return this.http.put<ITransactionCodeParams>(this.resourceUrl, transactionCodeParams, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITransactionCodeParams>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITransactionCodeParams[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
