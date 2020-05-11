import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILookupTypes } from 'app/shared/model/lookup-types.model';

type EntityResponseType = HttpResponse<ILookupTypes>;
type EntityArrayResponseType = HttpResponse<ILookupTypes[]>;

@Injectable({ providedIn: 'root' })
export class LookupTypesService {
  public resourceUrl = SERVER_API_URL + 'api/lookup-types';

  constructor(protected http: HttpClient) {}

  create(lookupTypes: ILookupTypes): Observable<EntityResponseType> {
    return this.http.post<ILookupTypes>(this.resourceUrl, lookupTypes, { observe: 'response' });
  }

  update(lookupTypes: ILookupTypes): Observable<EntityResponseType> {
    return this.http.put<ILookupTypes>(this.resourceUrl, lookupTypes, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILookupTypes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILookupTypes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
