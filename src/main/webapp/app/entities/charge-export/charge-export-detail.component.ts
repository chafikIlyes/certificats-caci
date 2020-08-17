import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IChargeExport } from 'app/shared/model/charge-export.model';

@Component({
  selector: 'jhi-charge-export-detail',
  templateUrl: './charge-export-detail.component.html',
})
export class ChargeExportDetailComponent implements OnInit {
  chargeExport: IChargeExport | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chargeExport }) => (this.chargeExport = chargeExport));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
