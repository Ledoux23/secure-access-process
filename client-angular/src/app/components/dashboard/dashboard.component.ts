import { Component } from '@angular/core';
import { JwtService } from '../../service/jwt.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  message: string = '';

  constructor(
    private service: JwtService
  ) { }

  ngOnInit() {
    this.home();
  }

  home() {
    this.service.home().subscribe(
      (response) => {
        console.log(response);
        this.message = response.message;
      }
    )
  }

}
