import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Offer } from 'src/app/models/offer.model';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent implements OnInit {
  user: User | undefined;
  offerForm = new FormGroup({
    jobId: new FormControl('', Validators.required),
    employeeUsername: new FormControl('', Validators.required),
    hourlyRate: new FormControl('', Validators.required),
    estimatedTime: new FormControl('', Validators.required),
    description: new FormControl(''),
    status: new FormControl(false)
  });

  constructor(private userService: UserService, private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      let jobId = params['jobId'];
      this.offerForm.get('jobId')!.setValue(jobId);
    });
    this.userService.getCurrentUser(localStorage.getItem('jwtToken') || '').subscribe(data => {
      this.user = data;
      this.offerForm.get('employeeUsername')!.setValue(this.user.username);
    });
  }
  

  onSubmit(): void {
    if (this.offerForm.valid) {
      const formValue = this.offerForm.value;
      if (formValue.jobId && formValue.hourlyRate && formValue.estimatedTime) {
        const offer: Offer = {
          jobId: Number(formValue.jobId),
          employeeUsername: formValue.employeeUsername || 'default', 
          hourlyRate: Number(formValue.hourlyRate),
          estimatedTime: Number(formValue.estimatedTime),
          description: formValue.description || '',
          status: formValue.status || false
        };
        this.createOffer(offer).subscribe({
          next: (data) => {            
            console.log(data);
          },
          error: (error) => {
            console.log(error);
          }
        });
      } else {
        console.error('Required form field is missing');
      }
      this.router.navigateByUrl('/home').catch(err => console.log(err));
    }
  } 
  
  createOffer(offer: Offer): Observable<Offer> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('jwtToken')
    });

    return this.http.post<Offer>('http://localhost:8080/api/offers', offer, { headers });
  }
}
