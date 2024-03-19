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
    this.welcome();
  }

  welcome() {
    this.service.welcome().subscribe(
      (response) => {
        console.log(response);
        this.message = response.message;
      }
    )
  }

}
