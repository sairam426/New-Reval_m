// import { RevalLookupsModule } from './../lookups/lookups.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { LookupTypesComponent } from './lookup-types.component';
import { LookupTypesDetailComponent } from './lookup-types-detail.component';
import { LookupTypesUpdateComponent } from './lookup-types-update.component';
import { LookupTypesDeleteDialogComponent } from './lookup-types-delete-dialog.component';
import { lookupTypesRoute } from './lookup-types.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(lookupTypesRoute)],
  declarations: [LookupTypesComponent, LookupTypesDetailComponent, LookupTypesUpdateComponent, LookupTypesDeleteDialogComponent],
  entryComponents: [LookupTypesDeleteDialogComponent]
})
export class RevalLookupTypesModule {}
