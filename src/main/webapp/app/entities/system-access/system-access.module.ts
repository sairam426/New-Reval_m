import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { SystemAccessComponent } from './system-access.component';
import { SystemAccessDetailComponent } from './system-access-detail.component';
import { SystemAccessUpdateComponent } from './system-access-update.component';
import { SystemAccessDeleteDialogComponent } from './system-access-delete-dialog.component';
import { systemAccessRoute } from './system-access.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(systemAccessRoute)],
  declarations: [SystemAccessComponent, SystemAccessDetailComponent, SystemAccessUpdateComponent, SystemAccessDeleteDialogComponent],
  entryComponents: [SystemAccessDeleteDialogComponent]
})
export class RevalSystemAccessModule {}
