import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/models/job.model';
import { JobService } from 'src/app/services/job.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';
import { Employee, EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  jobs!: Job[];
  user!: User;
  employees: Employee[] = [];

  constructor(private employeeService: EmployeeService, private userService: UserService, private jobService: JobService, private router: Router) { }

  ngOnInit(): void {
      this.jobService.getAllJobs().subscribe(data => {
          this.jobs = data;
      });

      this.userService.getCurrentUser(localStorage.getItem('jwtToken') || '').subscribe(data => {
        this.user = data;
      });

      this.employeeService.getEmployees().subscribe(data => {
        this.employees = data;
      });
  }

  redirectToOfferForm(jobId: number) {
    this.router.navigate(['/offer-form', jobId], {queryParams: { jobId: jobId }}).catch(err => console.log(err));
  }

  isEmployee(): boolean {
    return this.user?.role === 'EMPLOYEE';
  }

  alreadyApplied(): boolean {
    return this.user?.appliedJobs?.length !== 0;
  }
}
