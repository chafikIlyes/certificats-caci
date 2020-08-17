import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IChargeExport, ChargeExport } from 'app/shared/model/charge-export.model';
import { ChargeExportService } from './charge-export.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-charge-export-update',
  templateUrl: './charge-export-update.component.html',
})
export class ChargeExportUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    fonction: [null, [Validators.required]],
    tel: [null, [Validators.required]],
    fax: [null, [Validators.required]],
    mobile: [null, [Validators.required]],
    signature: [null, [Validators.required]],
    signatureContentType: [],
    cachet: [null, [Validators.required]],
    cachetContentType: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected chargeExportService: ChargeExportService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chargeExport }) => {
      this.updateForm(chargeExport);
    });
  }

  updateForm(chargeExport: IChargeExport): void {
    this.editForm.patchValue({
      id: chargeExport.id,
      nom: chargeExport.nom,
      prenom: chargeExport.prenom,
      fonction: chargeExport.fonction,
      tel: chargeExport.tel,
      fax: chargeExport.fax,
      mobile: chargeExport.mobile,
      signature: chargeExport.signature,
      signatureContentType: chargeExport.signatureContentType,
      cachet: chargeExport.cachet,
      cachetContentType: chargeExport.cachetContentType,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('certificatcaciApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const chargeExport = this.createFromForm();
    if (chargeExport.id !== undefined) {
      this.subscribeToSaveResponse(this.chargeExportService.update(chargeExport));
    } else {
      this.subscribeToSaveResponse(this.chargeExportService.create(chargeExport));
    }
  }

  private createFromForm(): IChargeExport {
    return {
      ...new ChargeExport(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      fonction: this.editForm.get(['fonction'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
      signatureContentType: this.editForm.get(['signatureContentType'])!.value,
      signature: this.editForm.get(['signature'])!.value,
      cachetContentType: this.editForm.get(['cachetContentType'])!.value,
      cachet: this.editForm.get(['cachet'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChargeExport>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
