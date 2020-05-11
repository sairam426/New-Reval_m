import { Component } from '@angular/core';
import { AccountService } from 'app/core/auth/account.service';
import { LoginService } from 'app/core/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-app-header1',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  public sidebarMinimized = false;

  constructor(private accountService: AccountService, private router: Router, private loginService: LoginService) {}

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  toggleMinimize(e: any): void {
    this.sidebarMinimized = e;
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['']);
  }
}
