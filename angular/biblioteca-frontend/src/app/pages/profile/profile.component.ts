import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  email = '';
  roles: string[] = [];

  constructor(public auth: AuthService, public router: Router) {}

  ngOnInit() {
    const token = this.auth.getToken();

    if (!token) {
      this.router.navigate(['/login']);
      return;
    }

    const payload = JSON.parse(atob(token.split('.')[1]));

    this.email = payload.sub;
    this.roles = payload.roles || [];
  }

  isAdmin() {
    return this.roles.includes('ADMIN');
  }

  isClient() {
    return this.roles.includes('CLIENTE') || this.roles.includes('USER');
  }
}
