import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IChargeExport } from 'app/shared/model/charge-export.model';

type EntityResponseType = HttpResponse<IChargeExport>;
type EntityArrayResponseType = HttpResponse<IChargeExport[]>;

@Injectable({ providedIn: 'root' })
export class ChargeExportService {
  public resourceUrl = SERVER_API_URL + 'api/charge-exports';

  constructor(protected http: HttpClient) {}

  create(chargeExport: IChargeExport): Observable<EntityResponseType> {
    return this.http.post<IChargeExport>(this.resourceUrl, chargeExport, { observe: 'response' });
  }

  update(chargeExport: IChargeExport): Observable<EntityResponseType> {
    return this.http.put<IChargeExport>(this.resourceUrl, chargeExport, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IChargeExport>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IChargeExport[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
