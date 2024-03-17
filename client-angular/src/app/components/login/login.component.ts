import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JwtService } from '../../service/jwt.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup | undefined;

  constructor(
    private service: JwtService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
    })
  }

  submitForm() {
    if (this.loginForm !== undefined) {
      console.log(this.loginForm.value);
      this.service.login(this.loginForm.value).subscribe(
        (response) => {
          console.log(response);
          if (response.jwt != null) {
            alert("Hello, Your token is " + response.jwt);
          }
        }
      )
    }
  }

}
