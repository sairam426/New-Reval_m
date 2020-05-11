import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { ProvidersComponent } from './providers.component';
import { ProvidersDetailComponent } from './providers-detail.component';
import { ProvidersUpdateComponent } from './providers-update.component';
import { ProvidersDeleteDialogComponent } from './providers-delete-dialog.component';
import { providersRoute } from './providers.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(providersRoute)],
  declarations: [ProvidersComponent, ProvidersDetailComponent, ProvidersUpdateComponent, ProvidersDeleteDialogComponent],
  entryComponents: [ProvidersDeleteDialogComponent]
})
export class RevalProvidersModule {}
