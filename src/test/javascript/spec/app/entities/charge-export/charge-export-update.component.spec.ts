import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CertificatcaciTestModule } from '../../../test.module';
import { ChargeExportUpdateComponent } from 'app/entities/charge-export/charge-export-update.component';
import { ChargeExportService } from 'app/entities/charge-export/charge-export.service';
import { ChargeExport } from 'app/shared/model/charge-export.model';

describe('Component Tests', () => {
  describe('ChargeExport Management Update Component', () => {
    let comp: ChargeExportUpdateComponent;
    let fixture: ComponentFixture<ChargeExportUpdateComponent>;
    let service: ChargeExportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CertificatcaciTestModule],
        declarations: [ChargeExportUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ChargeExportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChargeExportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChargeExportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ChargeExport(123);
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
        const entity = new ChargeExport();
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
