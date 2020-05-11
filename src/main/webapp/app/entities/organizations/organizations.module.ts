import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RevalSharedModule } from 'app/shared/shared.module';
import { OrganizationsComponent } from './organizations.component';
import { OrganizationsDetailComponent } from './organizations-detail.component';
import { OrganizationsUpdateComponent } from './organizations-update.component';
import { OrganizationsDeleteDialogComponent } from './organizations-delete-dialog.component';
import { organizationsRoute } from './organizations.route';

@NgModule({
  imports: [RevalSharedModule, RouterModule.forChild(organizationsRoute)],
  declarations: [OrganizationsComponent, OrganizationsDetailComponent, OrganizationsUpdateComponent, OrganizationsDeleteDialogComponent],
  entryComponents: [OrganizationsDeleteDialogComponent]
})
export class RevalOrganizationsModule {}
