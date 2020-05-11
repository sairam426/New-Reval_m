import { Component } from '@angular/core';
import { AccountService } from 'app/core/auth/account.service';
import { Router, ActivatedRouteSnapshot, NavigationEnd, NavigationError } from '@angular/router';

@Component({
  selector: 'jhi-app-layout',
  templateUrl: './layout.component.html'
})
export class LayoutComponent {
  public sidebarMinimized = false;

  constructor(private accountService: AccountService, private router: Router) {}

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  toggleMinimize(e: any): void {
    this.sidebarMinimized = e;
  }
}
