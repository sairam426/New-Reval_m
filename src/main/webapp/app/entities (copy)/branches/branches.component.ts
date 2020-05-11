import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBranches } from 'app/shared/model/branches.model';
import { BranchesService } from './branches.service';
import { BranchesDeleteDialogComponent } from './branches-delete-dialog.component';

@Component({
  selector: 'jhi-branches',
  templateUrl: './branches.component.html'
})
export class BranchesComponent implements OnInit, OnDestroy {
  branches?: IBranches[];
  eventSubscriber?: Subscription;

  constructor(protected branchesService: BranchesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.branchesService.query().subscribe((res: HttpResponse<IBranches[]>) => (this.branches = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBranches();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBranches): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBranches(): void {
    this.eventSubscriber = this.eventManager.subscribe('branchesListModification', () => this.loadAll());
  }

  delete(branches: IBranches): void {
    const modalRef = this.modalService.open(BranchesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.branches = branches;
  }
}
