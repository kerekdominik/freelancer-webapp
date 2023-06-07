import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User | undefined;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getCurrentUser(localStorage.getItem('jwtToken') || '').subscribe(data => {
      this.user = data;
    });
  }

  isEmployee(): boolean {
    return this.user?.role === 'EMPLOYEE';
  }
}
