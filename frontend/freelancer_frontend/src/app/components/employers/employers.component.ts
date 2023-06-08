import { Component, OnInit } from '@angular/core';
import { Employer, EmployerService } from 'src/app/services/employer.service';

@Component({
  selector: 'app-employers',
  templateUrl: './employers.component.html',
  styleUrls: ['./employers.component.css']
})
export class EmployersComponent implements OnInit {
  employers: Employer[] = [];

  constructor(private employerService: EmployerService) { }

  ngOnInit(): void {
    this.employerService.getEmployers().subscribe(data => {
      this.employers = data;
    });
  }
}
