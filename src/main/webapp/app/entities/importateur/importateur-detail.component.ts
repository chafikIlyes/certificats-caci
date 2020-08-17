import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImportateur } from 'app/shared/model/importateur.model';

@Component({
  selector: 'jhi-importateur-detail',
  templateUrl: './importateur-detail.component.html',
})
export class ImportateurDetailComponent implements OnInit {
  importateur: IImportateur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ importateur }) => (this.importateur = importateur));
  }

  previousState(): void {
    window.history.back();
  }
}
