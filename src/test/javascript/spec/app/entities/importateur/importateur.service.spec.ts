import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ImportateurService } from 'app/entities/importateur/importateur.service';
import { IImportateur, Importateur } from 'app/shared/model/importateur.model';

describe('Service Tests', () => {
  describe('Importateur Service', () => {
    let injector: TestBed;
    let service: ImportateurService;
    let httpMock: HttpTestingController;
    let elemDefault: IImportateur;
    let expectedResult: IImportateur | IImportateur[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ImportateurService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Importateur(
        0,
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

      it('should create a Importateur', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Importateur()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Importateur', () => {
        const returnedFromService = Object.assign(
          {
            raisonSocial: 'BBBBBB',
            formeJuridique: 'BBBBBB',
            secterActivite: 'BBBBBB',
            mobile: 'BBBBBB',
            telFix: 'BBBBBB',
            email: 'BBBBBB',
            siteWeb: 'BBBBBB',
            adresse: 'BBBBBB',
            typeImportateur: 'BBBBBB',
            fax: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Importateur', () => {
        const returnedFromService = Object.assign(
          {
            raisonSocial: 'BBBBBB',
            formeJuridique: 'BBBBBB',
            secterActivite: 'BBBBBB',
            mobile: 'BBBBBB',
            telFix: 'BBBBBB',
            email: 'BBBBBB',
            siteWeb: 'BBBBBB',
            adresse: 'BBBBBB',
            typeImportateur: 'BBBBBB',
            fax: 'BBBBBB',
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

      it('should delete a Importateur', () => {
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
