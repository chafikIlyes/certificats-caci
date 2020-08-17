import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IImportateur, Importateur } from 'app/shared/model/importateur.model';
import { ImportateurService } from './importateur.service';
import { IEntreprise } from 'app/shared/model/entreprise.model';
import { EntrepriseService } from 'app/entities/entreprise/entreprise.service';

@Component({
  selector: 'jhi-importateur-update',
  templateUrl: './importateur-update.component.html',
})
export class ImportateurUpdateComponent implements OnInit {
  isSaving = false;
  entreprises: IEntreprise[] = [];

  editForm = this.fb.group({
    id: [],
    raisonSocial: [null, [Validators.required]],
    formeJuridique: [null, [Validators.required]],
    secterActivite: [null, [Validators.required]],
    mobile: [null, [Validators.required]],
    telFix: [null, [Validators.required]],
    email: [null, [Validators.required]],
    siteWeb: [],
    adresse: [null, [Validators.required]],
    typeImportateur: [],
    fax: [],
    entreprise: [],
  });

  constructor(
    protected importateurService: ImportateurService,
    protected entrepriseService: EntrepriseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ importateur }) => {
      this.updateForm(importateur);

      this.entrepriseService.query().subscribe((res: HttpResponse<IEntreprise[]>) => (this.entreprises = res.body || []));
    });
  }

  updateForm(importateur: IImportateur): void {
    this.editForm.patchValue({
      id: importateur.id,
      raisonSocial: importateur.raisonSocial,
      formeJuridique: importateur.formeJuridique,
      secterActivite: importateur.secterActivite,
      mobile: importateur.mobile,
      telFix: importateur.telFix,
      email: importateur.email,
      siteWeb: importateur.siteWeb,
      adresse: importateur.adresse,
      typeImportateur: importateur.typeImportateur,
      fax: importateur.fax,
      entreprise: importateur.entreprise,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const importateur = this.createFromForm();
    if (importateur.id !== undefined) {
      this.subscribeToSaveResponse(this.importateurService.update(importateur));
    } else {
      this.subscribeToSaveResponse(this.importateurService.create(importateur));
    }
  }

  private createFromForm(): IImportateur {
    return {
      ...new Importateur(),
      id: this.editForm.get(['id'])!.value,
      raisonSocial: this.editForm.get(['raisonSocial'])!.value,
      formeJuridique: this.editForm.get(['formeJuridique'])!.value,
      secterActivite: this.editForm.get(['secterActivite'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
      telFix: this.editForm.get(['telFix'])!.value,
      email: this.editForm.get(['email'])!.value,
      siteWeb: this.editForm.get(['siteWeb'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      typeImportateur: this.editForm.get(['typeImportateur'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      entreprise: this.editForm.get(['entreprise'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IImportateur>>): void {
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

  trackById(index: number, item: IEntreprise): any {
    return item.id;
  }
}
