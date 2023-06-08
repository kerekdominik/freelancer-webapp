import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JobService } from 'src/app/services/job.service';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/models/category.model';
import { Job } from 'src/app/models/job.model';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-addjob',
  templateUrl: './addjob.component.html',
  styleUrls: ['./addjob.component.css']
})
export class AddJobComponent implements OnInit {
  jobForm: FormGroup;
  categories: string[];
  user: User | undefined;

  constructor(
    private fb: FormBuilder, 
    private router: Router,
    private jobService: JobService,
    private categoryService: CategoryService,
    private userService: UserService
  ) {
    this.jobForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      category: ['', Validators.required],
      employer: ['', Validators.required],
      experienceLevel: ['', [Validators.required, Validators.min(1), Validators.max(5)]]
    });

    this.categories = [];
  }

  ngOnInit() {
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories.map((category: Category) => category.name);
    });
    this.userService.getCurrentUser(localStorage.getItem('jwtToken') || '').subscribe(data => {
      this.user = data;
      this.jobForm.get('employer')!.setValue(this.user.username);
    });
  }

  onSubmit() {
    if (this.jobForm.valid) {
      const formValue = this.jobForm.value;
      const job: Job = {
        id: 0,
        title: formValue.title,
        description: formValue.description,
        price: formValue.price,
        category: formValue.category,
        employer: formValue.employer,
        experienceLevel: formValue.experienceLevel,
        skills: [],
        appliedEmployees: []
      };
      this.jobService.createJob(job, localStorage.getItem('jwtToken') || '').subscribe({
        next: () => {
          console.log('Job created successfully!');
        },
        error: (error) => {
          console.log(error);
        }
      });
      this.router.navigateByUrl('/myjobs').catch(err => console.log(err));
    }
  }
}
