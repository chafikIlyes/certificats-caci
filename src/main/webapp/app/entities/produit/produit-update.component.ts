import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProduit, Produit } from 'app/shared/model/produit.model';
import { ProduitService } from './produit.service';
import { IEntreprise } from 'app/shared/model/entreprise.model';
import { EntrepriseService } from 'app/entities/entreprise/entreprise.service';
import { ICertificatOrigine } from 'app/shared/model/certificat-origine.model';
import { CertificatOrigineService } from 'app/entities/certificat-origine/certificat-origine.service';

type SelectableEntity = IEntreprise | ICertificatOrigine;

@Component({
  selector: 'jhi-produit-update',
  templateUrl: './produit-update.component.html',
})
export class ProduitUpdateComponent implements OnInit {
  isSaving = false;
  entreprises: IEntreprise[] = [];
  certificatorigines: ICertificatOrigine[] = [];
  dateFactureDp: any;

  editForm = this.fb.group({
    id: [],
    nomProduit: [null, [Validators.required]],
    description: [null, [Validators.required]],
    marque: [],
    hsCode: [],
    qte: [null, [Validators.required]],
    uniteMesure: [null, [Validators.required]],
    nbrColi: [null, [Validators.required]],
    poidNet: [null, [Validators.required]],
    poidReel: [null, [Validators.required]],
    dateFacture: [null, [Validators.required]],
    numeroFacture: [],
    entreprise: [],
    certificatOrigine: [],
  });

  constructor(
    protected produitService: ProduitService,
    protected entrepriseService: EntrepriseService,
    protected certificatOrigineService: CertificatOrigineService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ produit }) => {
      this.updateForm(produit);

      this.entrepriseService.query().subscribe((res: HttpResponse<IEntreprise[]>) => (this.entreprises = res.body || []));

      this.certificatOrigineService
        .query()
        .subscribe((res: HttpResponse<ICertificatOrigine[]>) => (this.certificatorigines = res.body || []));
    });
  }

  updateForm(produit: IProduit): void {
    this.editForm.patchValue({
      id: produit.id,
      nomProduit: produit.nomProduit,
      description: produit.description,
      marque: produit.marque,
      hsCode: produit.hsCode,
      qte: produit.qte,
      uniteMesure: produit.uniteMesure,
      nbrColi: produit.nbrColi,
      poidNet: produit.poidNet,
      poidReel: produit.poidReel,
      dateFacture: produit.dateFacture,
      numeroFacture: produit.numeroFacture,
      entreprise: produit.entreprise,
      certificatOrigine: produit.certificatOrigine,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const produit = this.createFromForm();
    if (produit.id !== undefined) {
      this.subscribeToSaveResponse(this.produitService.update(produit));
    } else {
      this.subscribeToSaveResponse(this.produitService.create(produit));
    }
  }

  private createFromForm(): IProduit {
    return {
      ...new Produit(),
      id: this.editForm.get(['id'])!.value,
      nomProduit: this.editForm.get(['nomProduit'])!.value,
      description: this.editForm.get(['description'])!.value,
      marque: this.editForm.get(['marque'])!.value,
      hsCode: this.editForm.get(['hsCode'])!.value,
      qte: this.editForm.get(['qte'])!.value,
      uniteMesure: this.editForm.get(['uniteMesure'])!.value,
      nbrColi: this.editForm.get(['nbrColi'])!.value,
      poidNet: this.editForm.get(['poidNet'])!.value,
      poidReel: this.editForm.get(['poidReel'])!.value,
      dateFacture: this.editForm.get(['dateFacture'])!.value,
      numeroFacture: this.editForm.get(['numeroFacture'])!.value,
      entreprise: this.editForm.get(['entreprise'])!.value,
      certificatOrigine: this.editForm.get(['certificatOrigine'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduit>>): void {
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
