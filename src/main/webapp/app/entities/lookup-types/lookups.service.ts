import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILookups } from 'app/shared/model/lookups.model';

type EntityResponseType = HttpResponse<ILookups>;
type EntityArrayResponseType = HttpResponse<ILookups[]>;

@Injectable({ providedIn: 'root' })
export class LookupsService {
  public resourceUrl = SERVER_API_URL + 'api/lookups';

  constructor(protected http: HttpClient) {}

  create(lookups: ILookups): Observable<EntityResponseType> {
    return this.http.post<ILookups>(this.resourceUrl, lookups, { observe: 'response' });
  }

  update(lookups: ILookups): Observable<EntityResponseType> {
    return this.http.put<ILookups>(this.resourceUrl, lookups, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILookups>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILookups[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  lookupsById(id: number): Observable<EntityArrayResponseType>{
    return this.http.get<ILookups[]>(`${this.resourceUrl}/?lookUpTypeId.equals=${id}`, { observe: 'response' });
  }
}
