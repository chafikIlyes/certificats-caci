import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CertificatcaciTestModule } from '../../../test.module';
import { ImportateurDetailComponent } from 'app/entities/importateur/importateur-detail.component';
import { Importateur } from 'app/shared/model/importateur.model';

describe('Component Tests', () => {
  describe('Importateur Management Detail Component', () => {
    let comp: ImportateurDetailComponent;
    let fixture: ComponentFixture<ImportateurDetailComponent>;
    const route = ({ data: of({ importateur: new Importateur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [ImportateurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ImportateurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ImportateurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load importateur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.importateur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
