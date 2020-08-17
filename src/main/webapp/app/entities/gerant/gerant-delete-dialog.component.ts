import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGerant } from 'app/shared/model/gerant.model';
import { GerantService } from './gerant.service';

@Component({
  templateUrl: './gerant-delete-dialog.component.html',
})
export class GerantDeleteDialogComponent {
  gerant?: IGerant;

  constructor(protected gerantService: GerantService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gerantService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gerantListModification');
      this.activeModal.close();
    });
  }
}
