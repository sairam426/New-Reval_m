import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { LookupsComponent } from './lookups.component';
import { LookupsDetailComponent } from './lookups-detail.component';
import { LookupsUpdateComponent } from './lookups-update.component';
import { LookupsDeleteDialogComponent } from './lookups-delete-dialog.component';
import { lookupsRoute } from './lookups.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(lookupsRoute)],
  declarations: [LookupsComponent, LookupsDetailComponent, LookupsUpdateComponent, LookupsDeleteDialogComponent],
  entryComponents: [LookupsDeleteDialogComponent],
  exports: [LookupsComponent]
})
export class RevalLookupsModule {}
