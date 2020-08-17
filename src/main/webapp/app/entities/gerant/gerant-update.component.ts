import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGerant, Gerant } from 'app/shared/model/gerant.model';
import { GerantService } from './gerant.service';

@Component({
  selector: 'jhi-gerant-update',
  templateUrl: './gerant-update.component.html',
})
export class GerantUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    email: [null, [Validators.required]],
    tel: [null, [Validators.required]],
    fax: [],
    mobile: [null, [Validators.required]],
  });

  constructor(protected gerantService: GerantService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gerant }) => {
      this.updateForm(gerant);
    });
  }

  updateForm(gerant: IGerant): void {
    this.editForm.patchValue({
      id: gerant.id,
      nom: gerant.nom,
      prenom: gerant.prenom,
      email: gerant.email,
      tel: gerant.tel,
      fax: gerant.fax,
      mobile: gerant.mobile,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gerant = this.createFromForm();
    if (gerant.id !== undefined) {
      this.subscribeToSaveResponse(this.gerantService.update(gerant));
    } else {
      this.subscribeToSaveResponse(this.gerantService.create(gerant));
    }
  }

  private createFromForm(): IGerant {
    return {
      ...new Gerant(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      email: this.editForm.get(['email'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGerant>>): void {
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
