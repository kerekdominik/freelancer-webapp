import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Job } from 'src/app/models/job.model';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-myjobs',
  templateUrl: './myjobs.component.html',
  styleUrls: ['./myjobs.component.css']
})
export class MyJobsComponent implements OnInit {
  jobs!: Job[];

  constructor(private jobService: JobService, public router: Router) { }

  ngOnInit(): void {
    this.jobService.getMyJobs(localStorage.getItem('jwtToken') || '').subscribe(data => {
      this.jobs = data;
    });
  }

  addJob(): void {
    this.router.navigateByUrl('/myjobs/addjob');
  }

  editJob(): void {
    ;
  }

  deleteJob(): void {
    ;
  }
  redirectToAppliers(): void {
    ;
  }
}
