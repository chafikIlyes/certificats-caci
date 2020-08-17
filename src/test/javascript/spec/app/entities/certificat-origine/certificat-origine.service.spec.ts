import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CertificatOrigineService } from 'app/entities/certificat-origine/certificat-origine.service';
import { ICertificatOrigine, CertificatOrigine } from 'app/shared/model/certificat-origine.model';

describe('Service Tests', () => {
  describe('CertificatOrigine Service', () => {
    let injector: TestBed;
    let service: CertificatOrigineService;
    let httpMock: HttpTestingController;
    let elemDefault: ICertificatOrigine;
    let expectedResult: ICertificatOrigine | ICertificatOrigine[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CertificatOrigineService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CertificatOrigine(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CertificatOrigine', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CertificatOrigine()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CertificatOrigine', () => {
        const returnedFromService = Object.assign(
          {
            nomExportateur: 'BBBBBB',
            adresseExportateur: 'BBBBBB',
            nomProducteur: 'BBBBBB',
            adresseProducteur: 'BBBBBB',
            nomImportateur: 'BBBBBB',
            adresseImportateur: 'BBBBBB',
            payOrigine: 'BBBBBB',
            autreOrigine: true,
            payAutreOrigine: 'BBBBBB',
            detailTransport: 'BBBBBB',
            observation: 'BBBBBB',
            etatCertificat: 'BBBBBB',
            typeCertificats: 'BBBBBB',
            nomSignataire: 'BBBBBB',
            prenomSignataire: 'BBBBBB',
            emailSignataire: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CertificatOrigine', () => {
        const returnedFromService = Object.assign(
          {
            nomExportateur: 'BBBBBB',
            adresseExportateur: 'BBBBBB',
            nomProducteur: 'BBBBBB',
            adresseProducteur: 'BBBBBB',
            nomImportateur: 'BBBBBB',
            adresseImportateur: 'BBBBBB',
            payOrigine: 'BBBBBB',
            autreOrigine: true,
            payAutreOrigine: 'BBBBBB',
            detailTransport: 'BBBBBB',
            observation: 'BBBBBB',
            etatCertificat: 'BBBBBB',
            typeCertificats: 'BBBBBB',
            nomSignataire: 'BBBBBB',
            prenomSignataire: 'BBBBBB',
            emailSignataire: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CertificatOrigine', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
