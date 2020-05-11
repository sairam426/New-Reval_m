import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { BranchesComponent } from './branches.component';
import { BranchesDetailComponent } from './branches-detail.component';
import { BranchesUpdateComponent } from './branches-update.component';
import { BranchesDeleteDialogComponent } from './branches-delete-dialog.component';
import { branchesRoute } from './branches.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(branchesRoute)],
  declarations: [BranchesComponent, BranchesDetailComponent, BranchesUpdateComponent, BranchesDeleteDialogComponent],
  entryComponents: [BranchesDeleteDialogComponent]
})
export class RevalBranchesModule {}
