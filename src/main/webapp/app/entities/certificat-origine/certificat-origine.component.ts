import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICertificatOrigine } from 'app/shared/model/certificat-origine.model';
import { CertificatOrigineService } from './certificat-origine.service';
import { CertificatOrigineDeleteDialogComponent } from './certificat-origine-delete-dialog.component';

@Component({
  selector: 'jhi-certificat-origine',
  templateUrl: './certificat-origine.component.html',
})
export class CertificatOrigineComponent implements OnInit, OnDestroy {
  certificatOrigines?: ICertificatOrigine[];
  eventSubscriber?: Subscription;

  constructor(
    protected certificatOrigineService: CertificatOrigineService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.certificatOrigineService
      .query()
      .subscribe((res: HttpResponse<ICertificatOrigine[]>) => (this.certificatOrigines = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCertificatOrigines();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICertificatOrigine): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCertificatOrigines(): void {
    this.eventSubscriber = this.eventManager.subscribe('certificatOrigineListModification', () => this.loadAll());
  }

  delete(certificatOrigine: ICertificatOrigine): void {
    const modalRef = this.modalService.open(CertificatOrigineDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.certificatOrigine = certificatOrigine;
  }
}
