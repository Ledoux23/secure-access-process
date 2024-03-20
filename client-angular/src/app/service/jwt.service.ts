import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject  } from 'rxjs';

const BASE_URL = ["http://localhost:8080/"]

@Injectable({
  providedIn: 'root'
})

export class JwtService {

  constructor(private http: HttpClient) { }

  register(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'signup', signRequest)
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'login', loginRequest)
  }

  welcome(): Observable<any> {
    return this.http.get(BASE_URL + 'api/welcome', {
      headers: this.createAuthorizationHeader()
    })
  }

  
  private createAuthorizationHeader(): HttpHeaders {
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      console.log("JWT token found in local storage", jwtToken);
      return new HttpHeaders().set(
        "Authorization", "Bearer " + jwtToken
      );
    } else {
      console.log("JWT token not found in local storage");
      // If no token is found, return an empty HttpHeaders
      return new HttpHeaders();
    }
    
  }

  //a service to manage connection status
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this.isLoggedInSubject.asObservable(); 

  updateLoginStatus(status: boolean) {
    this.isLoggedInSubject.next(status);
  }

}
