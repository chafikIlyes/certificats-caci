import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGerant } from 'app/shared/model/gerant.model';

@Component({
  selector: 'jhi-gerant-detail',
  templateUrl: './gerant-detail.component.html',
})
export class GerantDetailComponent implements OnInit {
  gerant: IGerant | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gerant }) => (this.gerant = gerant));
  }

  previousState(): void {
    window.history.back();
  }
}
