import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";

export interface Employer {
    username: string;
    introduction: string;
  }
  
  @Injectable({
    providedIn: 'root'
  })
  export class EmployerService {
    constructor(private http: HttpClient) { }
  
    getEmployers(): Observable<Employer[]> {
      return this.http.get<Employer[]>('http://localhost:8080/api/users/employers');
    }
  }
  