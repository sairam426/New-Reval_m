import { LookupTypesService } from 'app/entities/lookup-types/lookup-types.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILookups } from 'app/shared/model/lookups.model';
import { LookupsService } from './lookups.service';
import { LookupsDeleteDialogComponent } from './lookups-delete-dialog.component';
import { ILookupTypes } from 'app/shared/model/lookup-types.model';

@Component({
  selector: 'jhi-lookups',
  templateUrl: './lookups.component.html'
})
export class LookupsComponent implements OnInit, OnDestroy {
  lookuptypes: ILookupTypes[] = [];
  lookupTypeSelected: any=[];
  lookups?: ILookups[];
  eventSubscriber?: Subscription;

  constructor(protected lookupsService: LookupsService, protected eventManager: JhiEventManager, protected modalService: NgbModal, protected lookupTypesService: LookupTypesService) {}

  loadAll(): void {
    this.lookupTypesService.query().subscribe((res: HttpResponse<ILookupTypes[]>) => (this.lookuptypes = res.body || []));
    this.lookupsService.query().subscribe((res: HttpResponse<ILookups[]>) => (this.lookups = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLookups();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILookups): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLookups(): void {
    this.eventSubscriber = this.eventManager.subscribe('lookupsListModification', () => this.loadAll());
  }

  delete(lookups: ILookups): void {
    const modalRef = this.modalService.open(LookupsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lookups = lookups;
  }
  lookupChanged():void{
    this.lookupsService.lookupsById(this.lookupTypeSelected['id']).subscribe((res: HttpResponse<ILookups[]>) => {(this.lookups = res.body || []);
      // alert(JSON.stringify(this.lookups))
    });
  }
}
