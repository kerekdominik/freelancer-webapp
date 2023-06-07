import { Component, OnInit } from '@angular/core';
import { AuthService } from './../../services/auth-services/auth.service';
import { Job } from 'src/app/models/job.model';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  jobs: Job[] = []; // Assuming Job is the name of your job model

  constructor(private jobService: JobService) { } // Assuming you have a job service to fetch job data

  ngOnInit(): void {
      this.jobService.getAllJobs().subscribe(data => {
          this.jobs = data;
      });
  }
}
