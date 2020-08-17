import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { CertificatcaciTestModule } from '../../../test.module';
import { EntrepriseDetailComponent } from 'app/entities/entreprise/entreprise-detail.component';
import { Entreprise } from 'app/shared/model/entreprise.model';

describe('Component Tests', () => {
  describe('Entreprise Management Detail Component', () => {
    let comp: EntrepriseDetailComponent;
    let fixture: ComponentFixture<EntrepriseDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ entreprise: new Entreprise(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [EntrepriseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EntrepriseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntrepriseDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load entreprise on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entreprise).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
