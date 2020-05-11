import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISystemAccess } from 'app/shared/model/system-access.model';

type EntityResponseType = HttpResponse<ISystemAccess>;
type EntityArrayResponseType = HttpResponse<ISystemAccess[]>;

@Injectable({ providedIn: 'root' })
export class SystemAccessService {
  public resourceUrl = SERVER_API_URL + 'api/system-accesses';

  constructor(protected http: HttpClient) {}

  create(systemAccess: ISystemAccess): Observable<EntityResponseType> {
    return this.http.post<ISystemAccess>(this.resourceUrl, systemAccess, { observe: 'response' });
  }

  update(systemAccess: ISystemAccess): Observable<EntityResponseType> {
    return this.http.put<ISystemAccess>(this.resourceUrl, systemAccess, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISystemAccess>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISystemAccess[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
