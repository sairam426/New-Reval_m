import { NgModule } from '@angular/core';
import { RevalSharedModule } from 'app/shared/shared.module';
import { LayoutComponent } from './layout/layout.component';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { AppAsideModule, AppBreadcrumbModule, AppHeaderModule, AppFooterModule, AppSidebarModule } from '@coreui/angular';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { RouterModule } from '@angular/router';

import { AppCustomFooterComponent } from './footer/footer.component';
import { RevalAppRoutingModule } from 'app/app-routing.module';
const APP_CONTAINERS = [LayoutComponent];

@NgModule({
  imports: [
    RouterModule,
    RevalSharedModule,
    RevalAppRoutingModule,
    AppAsideModule,
    AppBreadcrumbModule,
    AppHeaderModule,
    AppFooterModule,
    AppSidebarModule,
    PerfectScrollbarModule
  ],
  declarations: [HeaderComponent, SidebarComponent, AppCustomFooterComponent, ...APP_CONTAINERS],
  exports: [LayoutComponent]
})
export class CoreUiModule {}
