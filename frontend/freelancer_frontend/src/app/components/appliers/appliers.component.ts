import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee, EmployeeService } from 'src/app/services/employee.service';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-appliers',
  templateUrl: './appliers.component.html',
  styleUrls: ['./appliers.component.css']
})
export class AppliersComponent implements OnInit{
  employees: Employee[] = [];
  jobId!: number;

  constructor(private route: ActivatedRoute, private employeeService: EmployeeService, private jobService: JobService, private router: Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.jobId = params['jobId'];
    });
    this.employeeService.getEmployeesByJob(this.jobId).subscribe(data => {
      this.employees = data;
    });
  }
}
