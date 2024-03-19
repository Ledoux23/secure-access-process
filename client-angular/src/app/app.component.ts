import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'client-angular';

  constructor(private router: Router) {}

  logout() {
    localStorage.removeItem('jwt'); // Delete the JWT token stored in localStorage
    this.router.navigateByUrl("/home"); // Redirect to home page
  }

}
