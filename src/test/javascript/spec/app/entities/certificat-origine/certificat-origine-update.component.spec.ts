import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CertificatcaciTestModule } from '../../../test.module';
import { CertificatOrigineUpdateComponent } from 'app/entities/certificat-origine/certificat-origine-update.component';
import { CertificatOrigineService } from 'app/entities/certificat-origine/certificat-origine.service';
import { CertificatOrigine } from 'app/shared/model/certificat-origine.model';

describe('Component Tests', () => {
  describe('CertificatOrigine Management Update Component', () => {
    let comp: CertificatOrigineUpdateComponent;
    let fixture: ComponentFixture<CertificatOrigineUpdateComponent>;
    let service: CertificatOrigineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [CertificatOrigineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CertificatOrigineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CertificatOrigineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CertificatOrigineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CertificatOrigine(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CertificatOrigine();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
