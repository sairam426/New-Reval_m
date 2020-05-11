import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { RevalSharedModule } from 'app/shared/shared.module';
import { RevalCoreModule } from 'app/core/core.module';
import { RevalAppRoutingModule } from './app-routing.module';
import { RevalHomeModule } from './home/home.module';
import { RevalEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { CoreUiModule } from './layouts/coreui/coreui.module';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
NgbTabsetModule
@NgModule({
  imports: [
    BrowserModule,
    RevalSharedModule,
    RevalCoreModule,
    RevalHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    RevalEntityModule,
    RevalAppRoutingModule,
    CoreUiModule,
    NgbTabsetModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class RevalAppModule {}
