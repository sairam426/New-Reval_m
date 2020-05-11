import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICompanies } from 'app/shared/model/companies.model';
import { CompaniesService } from './companies.service';
import { CompaniesDeleteDialogComponent } from './companies-delete-dialog.component';

@Component({
  selector: 'jhi-companies',
  templateUrl: './companies.component.html'
})
export class CompaniesComponent implements OnInit, OnDestroy {
  companies?: ICompanies[];
  eventSubscriber?: Subscription;

  constructor(protected companiesService: CompaniesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.companiesService.query().subscribe((res: HttpResponse<ICompanies[]>) => (this.companies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCompanies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICompanies): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCompanies(): void {
    this.eventSubscriber = this.eventManager.subscribe('companiesListModification', () => this.loadAll());
  }

  delete(companies: ICompanies): void {
    const modalRef = this.modalService.open(CompaniesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.companies = companies;
  }
}
