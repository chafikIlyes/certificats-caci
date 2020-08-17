import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChargeExport } from 'app/shared/model/charge-export.model';
import { ChargeExportService } from './charge-export.service';

@Component({
  templateUrl: './charge-export-delete-dialog.component.html',
})
export class ChargeExportDeleteDialogComponent {
  chargeExport?: IChargeExport;

  constructor(
    protected chargeExportService: ChargeExportService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.chargeExportService.delete(id).subscribe(() => {
      this.eventManager.broadcast('chargeExportListModification');
      this.activeModal.close();
    });
  }
}
