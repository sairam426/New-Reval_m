import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProviders } from 'app/shared/model/providers.model';
import { ProvidersService } from './providers.service';
import { ProvidersDeleteDialogComponent } from './providers-delete-dialog.component';

@Component({
  selector: 'jhi-providers',
  templateUrl: './providers.component.html'
})
export class ProvidersComponent implements OnInit, OnDestroy {
  providers?: IProviders[];
  eventSubscriber?: Subscription;

  constructor(protected providersService: ProvidersService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.providersService.query().subscribe((res: HttpResponse<IProviders[]>) => (this.providers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProviders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProviders): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProviders(): void {
    this.eventSubscriber = this.eventManager.subscribe('providersListModification', () => this.loadAll());
  }

  delete(providers: IProviders): void {
    const modalRef = this.modalService.open(ProvidersDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.providers = providers;
  }
}
