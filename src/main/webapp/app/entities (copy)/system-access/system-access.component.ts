import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISystemAccess } from 'app/shared/model/system-access.model';
import { SystemAccessService } from './system-access.service';
import { SystemAccessDeleteDialogComponent } from './system-access-delete-dialog.component';

@Component({
  selector: 'jhi-system-access',
  templateUrl: './system-access.component.html'
})
export class SystemAccessComponent implements OnInit, OnDestroy {
  systemAccesses?: ISystemAccess[];
  eventSubscriber?: Subscription;

  constructor(
    protected systemAccessService: SystemAccessService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.systemAccessService.query().subscribe((res: HttpResponse<ISystemAccess[]>) => (this.systemAccesses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSystemAccesses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISystemAccess): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSystemAccesses(): void {
    this.eventSubscriber = this.eventManager.subscribe('systemAccessListModification', () => this.loadAll());
  }

  delete(systemAccess: ISystemAccess): void {
    const modalRef = this.modalService.open(SystemAccessDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.systemAccess = systemAccess;
  }
}
