import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Job } from 'src/app/models/job.model';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-myjobs',
  templateUrl: './myjobs.component.html',
  styleUrls: ['./myjobs.component.css']
})
export class MyJobsComponent implements OnInit {
  jobs!: Job[];

  constructor(private jobService: JobService, public router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.jobService.getMyJobs(localStorage.getItem('jwtToken') || '').subscribe(data => {
      this.jobs = data;
    });
  }

  addJob(): void {
    this.router.navigateByUrl('/myjobs/addjob').catch(err => console.log(err));
  }

  editJob(jobId: number): void {
    this.router.navigate(['/myjobs/editjob'], {queryParams: { jobId: jobId }}).catch(err => console.log(err));
  }

  deleteJob(jobId: number): void {  
    if (jobId) {
      this.jobService.deleteJob(jobId, localStorage.getItem('jwtToken') || '').subscribe({
        next: () => {
          console.log('Job deleted successfully');
          this.router.navigate(['/home']).catch(err => console.log(err));
        },
        error: (error) => {
          console.log(error);
        }
      });
    } else {
      console.error('Job ID is missing');
    }
  }
  
  redirectToAppliers(jobId: number): void {
    this.router.navigate(['/myjobs/' + jobId + '/appliers'], {queryParams: { jobId: jobId }}).catch(err => console.log(err));
  }
}
