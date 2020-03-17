import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public login: string;
  public password: string;
  private auth: string;

  get username(): string {
    return localStorage.getItem('user');
  }

  /**
   * Gets, and check if not empty, user login and password
   * Creates auth
   */

  onLogin(): void {
  if (this.login.trim() !== '' && this.password.trim() !== '') {
  localStorage.setItem('user', this.login);
  localStorage.setItem('password', this.password);
  this.auth = btoa(this.login + ':' + this.password);
  window.localStorage.setItem('auth', this.auth);
  }
}

  /**
   * Log out an user by removing his date from local Storage
   */

  onLogOut(): void {
    localStorage.removeItem('user');
    localStorage.removeItem('password');
    localStorage.removeItem('auth');
  }

  get logged(): boolean {
    return !!localStorage.getItem('user');
  }

  constructor() {
  }

  ngOnInit() {
  }

}
