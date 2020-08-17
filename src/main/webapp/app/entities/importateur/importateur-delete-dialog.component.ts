import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImportateur } from 'app/shared/model/importateur.model';
import { ImportateurService } from './importateur.service';

@Component({
  templateUrl: './importateur-delete-dialog.component.html',
})
export class ImportateurDeleteDialogComponent {
  importateur?: IImportateur;

  constructor(
    protected importateurService: ImportateurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.importateurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('importateurListModification');
      this.activeModal.close();
    });
  }
}
