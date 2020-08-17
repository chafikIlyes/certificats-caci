import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICertificatOrigine } from 'app/shared/model/certificat-origine.model';

type EntityResponseType = HttpResponse<ICertificatOrigine>;
type EntityArrayResponseType = HttpResponse<ICertificatOrigine[]>;

@Injectable({ providedIn: 'root' })
export class CertificatOrigineService {
  public resourceUrl = SERVER_API_URL + 'api/certificat-origines';

  constructor(protected http: HttpClient) {}

  create(certificatOrigine: ICertificatOrigine): Observable<EntityResponseType> {
    return this.http.post<ICertificatOrigine>(this.resourceUrl, certificatOrigine, { observe: 'response' });
  }

  update(certificatOrigine: ICertificatOrigine): Observable<EntityResponseType> {
    return this.http.put<ICertificatOrigine>(this.resourceUrl, certificatOrigine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICertificatOrigine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICertificatOrigine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
