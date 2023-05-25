import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  registerForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private formBuilder: FormBuilder, private router: Router, private http: HttpClient) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      passwordAgain: ['', Validators.required],
      role: ['', Validators.required],
      skills: [''],
      experience: [''],
      hourlyPrice: [''],
      introduction: ['']
    });
  }

  onRoleChange(event: Event): void {
    const role = (event.target as HTMLSelectElement).value;
    if (role === 'EMPLOYER') {
      this.registerForm.controls['skills'].clearValidators();
      this.registerForm.controls['experience'].clearValidators();
      this.registerForm.controls['hourlyPrice'].clearValidators();
      this.registerForm.controls['introduction'].setValidators([Validators.required]);
    } else {
      this.registerForm.controls['skills'].setValidators([Validators.required]);
      this.registerForm.controls['experience'].setValidators([Validators.required]);
      this.registerForm.controls['hourlyPrice'].setValidators([Validators.required]);
      this.registerForm.controls['introduction'].clearValidators();
    }
    this.registerForm.controls['skills'].updateValueAndValidity();
    this.registerForm.controls['experience'].updateValueAndValidity();
    this.registerForm.controls['hourlyPrice'].updateValueAndValidity();
    this.registerForm.controls['introduction'].updateValueAndValidity();
  }

  onSubmit(): void {
    const url = 'http://localhost:8080/api/auth/register';
    if (this.registerForm.valid) {
      this.http.post<any>(url, this.registerForm.value).subscribe({
        next: data => {
          
          localStorage.setItem('jwtToken', data.token);
          this.router.navigateByUrl('/home').catch(err => console.log(err));
        },
        error: err => {
          this.errorMessage = 'Error: ' + err.message;
        }
      });
    }
  }

  navigateToLogin() {
    this.router.navigateByUrl('/login').catch(err => console.log(err));
  }
}
