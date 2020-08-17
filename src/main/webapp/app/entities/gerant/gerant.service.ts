import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGerant } from 'app/shared/model/gerant.model';

type EntityResponseType = HttpResponse<IGerant>;
type EntityArrayResponseType = HttpResponse<IGerant[]>;

@Injectable({ providedIn: 'root' })
export class GerantService {
  public resourceUrl = SERVER_API_URL + 'api/gerants';

  constructor(protected http: HttpClient) {}

  create(gerant: IGerant): Observable<EntityResponseType> {
    return this.http.post<IGerant>(this.resourceUrl, gerant, { observe: 'response' });
  }

  update(gerant: IGerant): Observable<EntityResponseType> {
    return this.http.put<IGerant>(this.resourceUrl, gerant, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGerant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGerant[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
