import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IEntreprise, Entreprise } from 'app/shared/model/entreprise.model';
import { EntrepriseService } from './entreprise.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IGerant } from 'app/shared/model/gerant.model';
import { GerantService } from 'app/entities/gerant/gerant.service';
import { IChargeExport } from 'app/shared/model/charge-export.model';
import { ChargeExportService } from 'app/entities/charge-export/charge-export.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

type SelectableEntity = IGerant | IChargeExport | IUser;

@Component({
  selector: 'jhi-entreprise-update',
  templateUrl: './entreprise-update.component.html',
})
export class EntrepriseUpdateComponent implements OnInit {
  isSaving = false;
  gerants: IGerant[] = [];
  chargeexports: IChargeExport[] = [];
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    raisonSocial: [null, [Validators.required]],
    formeJuridique: [null, [Validators.required]],
    secterActivite: [null, [Validators.required]],
    rc: [null, [Validators.required]],
    rcContentType: [],
    nif: [null, [Validators.required]],
    nifContentType: [],
    nis: [null, [Validators.required]],
    nisContentType: [],
    codeActivite: [null, [Validators.required]],
    codeActiviteExport: [],
    mobile: [null, [Validators.required]],
    telFix: [null, [Validators.required]],
    email: [null, [Validators.required]],
    siteWeb: [],
    adresse: [null, [Validators.required]],
    typeExportateur: [],
    fax: [],
    soldeCertif: [],
    gerant: [],
    chargeExport: [],
    user: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected entrepriseService: EntrepriseService,
    protected gerantService: GerantService,
    protected chargeExportService: ChargeExportService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entreprise }) => {
      this.updateForm(entreprise);

      this.gerantService
        .query({ filter: 'entreprise-is-null' })
        .pipe(
          map((res: HttpResponse<IGerant[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGerant[]) => {
          if (!entreprise.gerant || !entreprise.gerant.id) {
            this.gerants = resBody;
          } else {
            this.gerantService
              .find(entreprise.gerant.id)
              .pipe(
                map((subRes: HttpResponse<IGerant>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGerant[]) => (this.gerants = concatRes));
          }
        });

      this.chargeExportService
        .query({ filter: 'entreprise-is-null' })
        .pipe(
          map((res: HttpResponse<IChargeExport[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IChargeExport[]) => {
          if (!entreprise.chargeExport || !entreprise.chargeExport.id) {
            this.chargeexports = resBody;
          } else {
            this.chargeExportService
              .find(entreprise.chargeExport.id)
              .pipe(
                map((subRes: HttpResponse<IChargeExport>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IChargeExport[]) => (this.chargeexports = concatRes));
          }
        });

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(entreprise: IEntreprise): void {
    this.editForm.patchValue({
      id: entreprise.id,
      raisonSocial: entreprise.raisonSocial,
      formeJuridique: entreprise.formeJuridique,
      secterActivite: entreprise.secterActivite,
      rc: entreprise.rc,
      rcContentType: entreprise.rcContentType,
      nif: entreprise.nif,
      nifContentType: entreprise.nifContentType,
      nis: entreprise.nis,
      nisContentType: entreprise.nisContentType,
      codeActivite: entreprise.codeActivite,
      codeActiviteExport: entreprise.codeActiviteExport,
      mobile: entreprise.mobile,
      telFix: entreprise.telFix,
      email: entreprise.email,
      siteWeb: entreprise.siteWeb,
      adresse: entreprise.adresse,
      typeExportateur: entreprise.typeExportateur,
      fax: entreprise.fax,
      soldeCertif: entreprise.soldeCertif,
      gerant: entreprise.gerant,
      chargeExport: entreprise.chargeExport,
      user: entreprise.user,
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

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entreprise = this.createFromForm();
    if (entreprise.id !== undefined) {
      this.subscribeToSaveResponse(this.entrepriseService.update(entreprise));
    } else {
      this.subscribeToSaveResponse(this.entrepriseService.create(entreprise));
    }
  }

  private createFromForm(): IEntreprise {
    return {
      ...new Entreprise(),
      id: this.editForm.get(['id'])!.value,
      raisonSocial: this.editForm.get(['raisonSocial'])!.value,
      formeJuridique: this.editForm.get(['formeJuridique'])!.value,
      secterActivite: this.editForm.get(['secterActivite'])!.value,
      rcContentType: this.editForm.get(['rcContentType'])!.value,
      rc: this.editForm.get(['rc'])!.value,
      nifContentType: this.editForm.get(['nifContentType'])!.value,
      nif: this.editForm.get(['nif'])!.value,
      nisContentType: this.editForm.get(['nisContentType'])!.value,
      nis: this.editForm.get(['nis'])!.value,
      codeActivite: this.editForm.get(['codeActivite'])!.value,
      codeActiviteExport: this.editForm.get(['codeActiviteExport'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
      telFix: this.editForm.get(['telFix'])!.value,
      email: this.editForm.get(['email'])!.value,
      siteWeb: this.editForm.get(['siteWeb'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      typeExportateur: this.editForm.get(['typeExportateur'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      soldeCertif: this.editForm.get(['soldeCertif'])!.value,
      gerant: this.editForm.get(['gerant'])!.value,
      chargeExport: this.editForm.get(['chargeExport'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntreprise>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
