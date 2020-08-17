import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICertificatOrigine } from 'app/shared/model/certificat-origine.model';

@Component({
  selector: 'jhi-certificat-origine-detail',
  templateUrl: './certificat-origine-detail.component.html',
})
export class CertificatOrigineDetailComponent implements OnInit {
  certificatOrigine: ICertificatOrigine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ certificatOrigine }) => (this.certificatOrigine = certificatOrigine));
  }

  previousState(): void {
    window.history.back();
  }
}
