import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EntrepriseService } from 'app/entities/entreprise/entreprise.service';
import { IEntreprise, Entreprise } from 'app/shared/model/entreprise.model';

describe('Service Tests', () => {
  describe('Entreprise Service', () => {
    let injector: TestBed;
    let service: EntrepriseService;
    let httpMock: HttpTestingController;
    let elemDefault: IEntreprise;
    let expectedResult: IEntreprise | IEntreprise[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EntrepriseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Entreprise(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a Entreprise', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Entreprise()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Entreprise', () => {
        const returnedFromService = Object.assign(
          {
            raisonSocial: 'BBBBBB',
            formeJuridique: 'BBBBBB',
            secterActivite: 'BBBBBB',
            rc: 'BBBBBB',
            nif: 'BBBBBB',
            nis: 'BBBBBB',
            codeActivite: 'BBBBBB',
            codeActiviteExport: 'BBBBBB',
            mobile: 'BBBBBB',
            telFix: 'BBBBBB',
            email: 'BBBBBB',
            siteWeb: 'BBBBBB',
            adresse: 'BBBBBB',
            typeExportateur: 'BBBBBB',
            fax: 'BBBBBB',
            soldeCertif: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Entreprise', () => {
        const returnedFromService = Object.assign(
          {
            raisonSocial: 'BBBBBB',
            formeJuridique: 'BBBBBB',
            secterActivite: 'BBBBBB',
            rc: 'BBBBBB',
            nif: 'BBBBBB',
            nis: 'BBBBBB',
            codeActivite: 'BBBBBB',
            codeActiviteExport: 'BBBBBB',
            mobile: 'BBBBBB',
            telFix: 'BBBBBB',
            email: 'BBBBBB',
            siteWeb: 'BBBBBB',
            adresse: 'BBBBBB',
            typeExportateur: 'BBBBBB',
            fax: 'BBBBBB',
            soldeCertif: 'BBBBBB',
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

      it('should delete a Entreprise', () => {
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
