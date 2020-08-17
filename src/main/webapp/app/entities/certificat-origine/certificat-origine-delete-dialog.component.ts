import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICertificatOrigine } from 'app/shared/model/certificat-origine.model';
import { CertificatOrigineService } from './certificat-origine.service';

@Component({
  templateUrl: './certificat-origine-delete-dialog.component.html',
})
export class CertificatOrigineDeleteDialogComponent {
  certificatOrigine?: ICertificatOrigine;

  constructor(
    protected certificatOrigineService: CertificatOrigineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.certificatOrigineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('certificatOrigineListModification');
      this.activeModal.close();
    });
  }
}
