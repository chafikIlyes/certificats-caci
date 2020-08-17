import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CertificatcaciTestModule } from '../../../test.module';
import { ImportateurUpdateComponent } from 'app/entities/importateur/importateur-update.component';
import { ImportateurService } from 'app/entities/importateur/importateur.service';
import { Importateur } from 'app/shared/model/importateur.model';

describe('Component Tests', () => {
  describe('Importateur Management Update Component', () => {
    let comp: ImportateurUpdateComponent;
    let fixture: ComponentFixture<ImportateurUpdateComponent>;
    let service: ImportateurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [ImportateurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ImportateurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ImportateurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImportateurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Importateur(123);
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
        const entity = new Importateur();
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
