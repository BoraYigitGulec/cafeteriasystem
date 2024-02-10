/*import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    canAcess = false;
  constructor(private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.canAcess) {
      return true;
    }

    // If the user is not logged in or doesn't have access, redirect to the login page or any other page you want
    this.router.navigate(['/']); // Change this to the path of your login page
    return false;
  }
}*/