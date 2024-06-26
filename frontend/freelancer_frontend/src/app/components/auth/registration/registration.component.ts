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
  passwordMismatch: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private http: HttpClient) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      passwordAgain: ['', Validators.required],
      role: ['', Validators.required],
      skills: [''],
      experienceLevel: [''],
      hourlyPrice: [''],
      introduction: ['']
    });
  }

  comparePasswords(): void {
    const password = this.registerForm.get('password')?.value;
    const passwordAgain = this.registerForm.get('passwordAgain')?.value;

    if (password !== passwordAgain) {
      this.passwordMismatch = true;
    } else {
      this.passwordMismatch = false;
    }
  }

  onRoleChange(event: Event): void {
    const role = (event.target as HTMLSelectElement).value;
    if (role === 'EMPLOYER') {
      this.registerForm.controls['skills'].clearValidators();
      this.registerForm.controls['experienceLevel'].clearValidators();
      this.registerForm.controls['hourlyPrice'].clearValidators();
      this.registerForm.controls['introduction'].setValidators([Validators.required]);
    } else {
      this.registerForm.controls['skills'].setValidators([Validators.required]);
      this.registerForm.controls['experienceLevel'].setValidators([Validators.required]);
      this.registerForm.controls['hourlyPrice'].setValidators([Validators.required]);
      this.registerForm.controls['introduction'].clearValidators();
    }
    this.registerForm.controls['skills'].updateValueAndValidity();
    this.registerForm.controls['experienceLevel'].updateValueAndValidity();
    this.registerForm.controls['hourlyPrice'].updateValueAndValidity();
    this.registerForm.controls['introduction'].updateValueAndValidity();
  }

  onSubmit(): void {
    const url = 'http://localhost:8080/api/auth/register';
    if (this.registerForm.valid && !this.passwordMismatch) {
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
