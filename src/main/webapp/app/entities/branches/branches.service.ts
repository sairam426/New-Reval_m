import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBranches } from 'app/shared/model/branches.model';

type EntityResponseType = HttpResponse<IBranches>;
type EntityArrayResponseType = HttpResponse<IBranches[]>;

@Injectable({ providedIn: 'root' })
export class BranchesService {
  public resourceUrl = SERVER_API_URL + 'api/branches';

  constructor(protected http: HttpClient) {}

  create(branches: IBranches): Observable<EntityResponseType> {
    return this.http.post<IBranches>(this.resourceUrl, branches, { observe: 'response' });
  }

  update(branches: IBranches): Observable<EntityResponseType> {
    return this.http.put<IBranches>(this.resourceUrl, branches, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBranches>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBranches[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
