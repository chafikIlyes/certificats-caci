import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CertificatcaciTestModule } from '../../../test.module';
import { CertificatOrigineComponent } from 'app/entities/certificat-origine/certificat-origine.component';
import { CertificatOrigineService } from 'app/entities/certificat-origine/certificat-origine.service';
import { CertificatOrigine } from 'app/shared/model/certificat-origine.model';

describe('Component Tests', () => {
  describe('CertificatOrigine Management Component', () => {
    let comp: CertificatOrigineComponent;
    let fixture: ComponentFixture<CertificatOrigineComponent>;
    let service: CertificatOrigineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [CertificatOrigineComponent],
      })
        .overrideTemplate(CertificatOrigineComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CertificatOrigineComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CertificatOrigineService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CertificatOrigine(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.certificatOrigines && comp.certificatOrigines[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
