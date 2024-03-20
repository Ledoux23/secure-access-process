import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from './service/jwt.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'client-angular';

  isLoggedIn: boolean = false; // to check if the user is logged in

  constructor(
    private router: Router,
    private service: JwtService
  ) {}

  ngOnInit() {
    // checking for the presence of the JWT token
    // this.isLoggedIn = !!localStorage.getItem('jwt');

    this.service.isLoggedIn$.subscribe((isLoggedIn) => {
      this.isLoggedIn = isLoggedIn;
    });

  }

  logout() {
    localStorage.removeItem('jwt'); // Delete the JWT token stored in localStorage
    this.service.updateLoginStatus(false); // Update isLoggedIn status
    this.router.navigateByUrl("/home"); // Redirect to home page    
  }

  isLoginPage(): boolean {
    return this.router.url === '/login';
  }

  isRegisterPage(): boolean {
    return this.router.url === '/register';
  }

}
