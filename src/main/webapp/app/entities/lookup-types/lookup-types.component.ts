import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILookupTypes } from 'app/shared/model/lookup-types.model';
import { LookupTypesService } from './lookup-types.service';
import { LookupTypesDeleteDialogComponent } from './lookup-types-delete-dialog.component';

@Component({
  selector: 'jhi-lookup-types',
  templateUrl: './lookup-types.component.html'
})
export class LookupTypesComponent implements OnInit, OnDestroy {
  lookupTypes?: ILookupTypes[];
  eventSubscriber?: Subscription;

  constructor(
    protected lookupTypesService: LookupTypesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.lookupTypesService.query().subscribe((res: HttpResponse<ILookupTypes[]>) => (this.lookupTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLookupTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILookupTypes): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLookupTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('lookupTypesListModification', () => this.loadAll());
  }

  delete(lookupTypes: ILookupTypes): void {
    const modalRef = this.modalService.open(LookupTypesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lookupTypes = lookupTypes;
  }
}
