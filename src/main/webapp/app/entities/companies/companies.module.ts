import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { CompaniesComponent } from './companies.component';
import { CompaniesDetailComponent } from './companies-detail.component';
import { CompaniesUpdateComponent } from './companies-update.component';
import { CompaniesDeleteDialogComponent } from './companies-delete-dialog.component';
import { companiesRoute } from './companies.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(companiesRoute)],
  declarations: [CompaniesComponent, CompaniesDetailComponent, CompaniesUpdateComponent, CompaniesDeleteDialogComponent],
  entryComponents: [CompaniesDeleteDialogComponent]
})
export class RevalCompaniesModule {}
