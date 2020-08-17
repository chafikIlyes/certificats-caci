import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CertificatcaciTestModule } from '../../../test.module';
import { GerantUpdateComponent } from 'app/entities/gerant/gerant-update.component';
import { GerantService } from 'app/entities/gerant/gerant.service';
import { Gerant } from 'app/shared/model/gerant.model';

describe('Component Tests', () => {
  describe('Gerant Management Update Component', () => {
    let comp: GerantUpdateComponent;
    let fixture: ComponentFixture<GerantUpdateComponent>;
    let service: GerantService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [GerantUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GerantUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GerantUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GerantService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Gerant(123);
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
        const entity = new Gerant();
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
