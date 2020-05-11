import { LookupsDeleteDialogComponent } from './lookups-delete-dialog.component';
// import { RevalLookupsModule } from './../lookups/lookups.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { LookupTypesComponent } from './lookup-types.component';
import { LookupTypesDetailComponent } from './lookup-types-detail.component';
import { LookupTypesUpdateComponent } from './lookup-types-update.component';
import { LookupTypesDeleteDialogComponent } from './lookup-types-delete-dialog.component';
import { LookupsComponent } from './lookups.component';
import { LookupsDetailComponent } from './lookups-detail.component';
import { LookupsUpdateComponent } from './lookups-update.component';
import { lookupTypesRoute } from './lookup-types.route';
import { lookupsRoute } from './lookups.route';

LookupsDeleteDialogComponent
@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(lookupTypesRoute), RouterModule.forChild(lookupsRoute)],
  declarations: [LookupTypesComponent, LookupTypesDetailComponent, LookupTypesUpdateComponent, LookupTypesDeleteDialogComponent,LookupsComponent, LookupsDetailComponent, LookupsUpdateComponent, LookupsDeleteDialogComponent],
  entryComponents: [LookupTypesDeleteDialogComponent, LookupsDeleteDialogComponent]
})
export class RevalLookupTypesModule {}
