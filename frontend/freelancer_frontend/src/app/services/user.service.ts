import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersUrl = 'http://localhost:8080/api/users/profile';

  constructor(private http: HttpClient) { }

  getCurrentUser(token: string): Observable<User> {
    const headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    return this.http.get<User>(this.usersUrl, { headers: headers });
  }  
}
