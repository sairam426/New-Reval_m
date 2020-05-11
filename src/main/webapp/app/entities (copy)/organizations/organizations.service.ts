import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrganizations } from 'app/shared/model/organizations.model';

type EntityResponseType = HttpResponse<IOrganizations>;
type EntityArrayResponseType = HttpResponse<IOrganizations[]>;

@Injectable({ providedIn: 'root' })
export class OrganizationsService {
  public resourceUrl = SERVER_API_URL + 'api/organizations';

  constructor(protected http: HttpClient) {}

  create(organizations: IOrganizations): Observable<EntityResponseType> {
    return this.http.post<IOrganizations>(this.resourceUrl, organizations, { observe: 'response' });
  }

  update(organizations: IOrganizations): Observable<EntityResponseType> {
    return this.http.put<IOrganizations>(this.resourceUrl, organizations, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrganizations>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrganizations[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
