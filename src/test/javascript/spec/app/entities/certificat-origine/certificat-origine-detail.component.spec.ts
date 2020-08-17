import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CertificatcaciTestModule } from '../../../test.module';
import { CertificatOrigineDetailComponent } from 'app/entities/certificat-origine/certificat-origine-detail.component';
import { CertificatOrigine } from 'app/shared/model/certificat-origine.model';

describe('Component Tests', () => {
  describe('CertificatOrigine Management Detail Component', () => {
    let comp: CertificatOrigineDetailComponent;
    let fixture: ComponentFixture<CertificatOrigineDetailComponent>;
    const route = ({ data: of({ certificatOrigine: new CertificatOrigine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [CertificatOrigineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CertificatOrigineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CertificatOrigineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load certificatOrigine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.certificatOrigine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
