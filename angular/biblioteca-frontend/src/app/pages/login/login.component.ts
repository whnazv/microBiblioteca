import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username = '';
  password = '';
  error = '';

  constructor(private auth: AuthService) {}

  login() {
    console.log("Enviando:", this.username, this.password);

    this.auth.login(this.username, this.password).subscribe({
      next: () => {
        console.log('Login OK');
        this.error = '';
      },
      error: () => {
        this.error = 'Credenciales incorrectas';
      }
    });
  }
}
