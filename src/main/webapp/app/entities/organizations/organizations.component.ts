import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrganizations } from 'app/shared/model/organizations.model';
import { OrganizationsService } from './organizations.service';
import { OrganizationsDeleteDialogComponent } from './organizations-delete-dialog.component';

@Component({
  selector: 'jhi-organizations',
  templateUrl: './organizations.component.html'
})
export class OrganizationsComponent implements OnInit, OnDestroy {
  organizations?: IOrganizations[];
  eventSubscriber?: Subscription;

  constructor(
    protected organizationsService: OrganizationsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.organizationsService.query().subscribe((res: HttpResponse<IOrganizations[]>) => (this.organizations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrganizations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrganizations): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrganizations(): void {
    this.eventSubscriber = this.eventManager.subscribe('organizationsListModification', () => this.loadAll());
  }

  delete(organizations: IOrganizations): void {
    const modalRef = this.modalService.open(OrganizationsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.organizations = organizations;
  }
}
