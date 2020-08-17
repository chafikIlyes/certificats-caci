import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CertificatcaciTestModule } from '../../../test.module';
import { GerantDetailComponent } from 'app/entities/gerant/gerant-detail.component';
import { Gerant } from 'app/shared/model/gerant.model';

describe('Component Tests', () => {
  describe('Gerant Management Detail Component', () => {
    let comp: GerantDetailComponent;
    let fixture: ComponentFixture<GerantDetailComponent>;
    const route = ({ data: of({ gerant: new Gerant(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [GerantDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GerantDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GerantDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gerant on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gerant).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
