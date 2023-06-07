import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";

export interface Employee {
    username: string;
    skills: string[];
    experienceLevel: number;
    hourlyPrice: number;
  }
  
  @Injectable({
    providedIn: 'root'
  })
  export class EmployeeService {
    constructor(private http: HttpClient) { }
  
    getEmployees(): Observable<Employee[]> {
      return this.http.get<Employee[]>('http://localhost:8080/api/users/employees');
    }
  }
  