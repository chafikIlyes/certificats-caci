import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { CertificatcaciTestModule } from '../../../test.module';
import { ChargeExportDetailComponent } from 'app/entities/charge-export/charge-export-detail.component';
import { ChargeExport } from 'app/shared/model/charge-export.model';

describe('Component Tests', () => {
  describe('ChargeExport Management Detail Component', () => {
    let comp: ChargeExportDetailComponent;
    let fixture: ComponentFixture<ChargeExportDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ chargeExport: new ChargeExport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [ChargeExportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ChargeExportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChargeExportDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load chargeExport on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.chargeExport).toEqual(jasmine.objectContaining({ id: 123 }));
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
