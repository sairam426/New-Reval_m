import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICompanies } from 'app/shared/model/companies.model';

type EntityResponseType = HttpResponse<ICompanies>;
type EntityArrayResponseType = HttpResponse<ICompanies[]>;

@Injectable({ providedIn: 'root' })
export class CompaniesService {
  public resourceUrl = SERVER_API_URL + 'api/companies';

  constructor(protected http: HttpClient) {}

  create(companies: ICompanies): Observable<EntityResponseType> {
    return this.http.post<ICompanies>(this.resourceUrl, companies, { observe: 'response' });
  }

  update(companies: ICompanies): Observable<EntityResponseType> {
    return this.http.put<ICompanies>(this.resourceUrl, companies, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICompanies>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICompanies[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
