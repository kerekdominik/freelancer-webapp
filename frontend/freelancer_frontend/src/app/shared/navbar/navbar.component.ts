import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user: User | undefined;

  constructor(private userService: UserService, public router: Router) { }

  ngOnInit(): void {
    this.userService.getCurrentUser(localStorage.getItem('jwtToken') || '').subscribe(data => {
      this.user = data;
    });
  }

  onLogout() {
    this.router.navigateByUrl('/login');
  }

  isEmployee(): boolean {
    return this.user?.role === 'EMPLOYEE';
  }
}
