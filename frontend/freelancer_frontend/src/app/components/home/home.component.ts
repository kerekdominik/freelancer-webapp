import { Component, OnInit } from '@angular/core';
import { AuthService } from './../../services/auth-services/auth.service';
import { Job } from 'src/app/models/job.model';
import { JobService } from 'src/app/services/job.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  jobs: Job[] = []; // Assuming Job is the name of your job model

  constructor(private jobService: JobService, private router: Router) { } // Assuming you have a job service to fetch job data

  ngOnInit(): void {
      this.jobService.getAllJobs().subscribe(data => {
          this.jobs = data;
      });
  }

  redirectToOfferForm(jobId: number) {
    this.router.navigate(['/offer-form', jobId], {queryParams: { jobId: jobId }});
  }
}
