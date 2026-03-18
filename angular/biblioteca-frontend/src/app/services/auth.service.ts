import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private apiUrl = 'http://localhost:8080/auth/login';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = {
      username: username,
      password: password
    };

    return this.http.post<any>(this.apiUrl, body, { headers }).pipe(
      tap(res => {
        console.log("Token recibido:", res.accessToken);
        localStorage.setItem('token', res.accessToken);
      })
    );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
  }
}
