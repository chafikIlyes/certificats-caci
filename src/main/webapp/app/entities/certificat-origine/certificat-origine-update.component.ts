import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICertificatOrigine, CertificatOrigine } from 'app/shared/model/certificat-origine.model';
import { CertificatOrigineService } from './certificat-origine.service';
import { IImportateur } from 'app/shared/model/importateur.model';
import { ImportateurService } from 'app/entities/importateur/importateur.service';
import { IEntreprise } from 'app/shared/model/entreprise.model';
import { EntrepriseService } from 'app/entities/entreprise/entreprise.service';

type SelectableEntity = IImportateur | IEntreprise;

@Component({
  selector: 'jhi-certificat-origine-update',
  templateUrl: './certificat-origine-update.component.html',
})
export class CertificatOrigineUpdateComponent implements OnInit {
  isSaving = false;
  importateurs: IImportateur[] = [];
  entreprises: IEntreprise[] = [];

  editForm = this.fb.group({
    id: [],
    nomExportateur: [null, [Validators.required]],
    adresseExportateur: [null, [Validators.required]],
    nomProducteur: [null, [Validators.required]],
    adresseProducteur: [null, [Validators.required]],
    nomImportateur: [null, [Validators.required]],
    adresseImportateur: [null, [Validators.required]],
    payOrigine: [null, [Validators.required]],
    autreOrigine: [],
    payAutreOrigine: [],
    detailTransport: [null, [Validators.required]],
    observation: [],
    etatCertificat: [],
    typeCertificats: [],
    nomSignataire: [],
    prenomSignataire: [],
    emailSignataire: [],
    importateur: [],
    entreprise: [],
  });

  constructor(
    protected certificatOrigineService: CertificatOrigineService,
    protected importateurService: ImportateurService,
    protected entrepriseService: EntrepriseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ certificatOrigine }) => {
      this.updateForm(certificatOrigine);

      this.importateurService
        .query({ filter: 'certificatorigine-is-null' })
        .pipe(
          map((res: HttpResponse<IImportateur[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IImportateur[]) => {
          if (!certificatOrigine.importateur || !certificatOrigine.importateur.id) {
            this.importateurs = resBody;
          } else {
            this.importateurService
              .find(certificatOrigine.importateur.id)
              .pipe(
                map((subRes: HttpResponse<IImportateur>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IImportateur[]) => (this.importateurs = concatRes));
          }
        });

      this.entrepriseService.query().subscribe((res: HttpResponse<IEntreprise[]>) => (this.entreprises = res.body || []));
    });
  }

  updateForm(certificatOrigine: ICertificatOrigine): void {
    this.editForm.patchValue({
      id: certificatOrigine.id,
      nomExportateur: certificatOrigine.nomExportateur,
      adresseExportateur: certificatOrigine.adresseExportateur,
      nomProducteur: certificatOrigine.nomProducteur,
      adresseProducteur: certificatOrigine.adresseProducteur,
      nomImportateur: certificatOrigine.nomImportateur,
      adresseImportateur: certificatOrigine.adresseImportateur,
      payOrigine: certificatOrigine.payOrigine,
      autreOrigine: certificatOrigine.autreOrigine,
      payAutreOrigine: certificatOrigine.payAutreOrigine,
      detailTransport: certificatOrigine.detailTransport,
      observation: certificatOrigine.observation,
      etatCertificat: certificatOrigine.etatCertificat,
      typeCertificats: certificatOrigine.typeCertificats,
      nomSignataire: certificatOrigine.nomSignataire,
      prenomSignataire: certificatOrigine.prenomSignataire,
      emailSignataire: certificatOrigine.emailSignataire,
      importateur: certificatOrigine.importateur,
      entreprise: certificatOrigine.entreprise,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const certificatOrigine = this.createFromForm();
    if (certificatOrigine.id !== undefined) {
      this.subscribeToSaveResponse(this.certificatOrigineService.update(certificatOrigine));
    } else {
      this.subscribeToSaveResponse(this.certificatOrigineService.create(certificatOrigine));
    }
  }

  private createFromForm(): ICertificatOrigine {
    return {
      ...new CertificatOrigine(),
      id: this.editForm.get(['id'])!.value,
      nomExportateur: this.editForm.get(['nomExportateur'])!.value,
      adresseExportateur: this.editForm.get(['adresseExportateur'])!.value,
      nomProducteur: this.editForm.get(['nomProducteur'])!.value,
      adresseProducteur: this.editForm.get(['adresseProducteur'])!.value,
      nomImportateur: this.editForm.get(['nomImportateur'])!.value,
      adresseImportateur: this.editForm.get(['adresseImportateur'])!.value,
      payOrigine: this.editForm.get(['payOrigine'])!.value,
      autreOrigine: this.editForm.get(['autreOrigine'])!.value,
      payAutreOrigine: this.editForm.get(['payAutreOrigine'])!.value,
      detailTransport: this.editForm.get(['detailTransport'])!.value,
      observation: this.editForm.get(['observation'])!.value,
      etatCertificat: this.editForm.get(['etatCertificat'])!.value,
      typeCertificats: this.editForm.get(['typeCertificats'])!.value,
      nomSignataire: this.editForm.get(['nomSignataire'])!.value,
      prenomSignataire: this.editForm.get(['prenomSignataire'])!.value,
      emailSignataire: this.editForm.get(['emailSignataire'])!.value,
      importateur: this.editForm.get(['importateur'])!.value,
      entreprise: this.editForm.get(['entreprise'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICertificatOrigine>>): void {
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
