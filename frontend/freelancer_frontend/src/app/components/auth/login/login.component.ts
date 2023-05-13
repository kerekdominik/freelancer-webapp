import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  errorMessage!: string;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    const url = 'http://localhost:8080/api/auth/login';
    this.http.post<any>(url, this.loginForm.value).subscribe({
      next: data => {
        // A válaszul kapott token mentése
        localStorage.setItem('jwtToken', data.token);
        // Átirányítás a kezdőlapra
        this.router.navigate(['/home']);
      },
      error: err => {
        // Hibakezelés
        this.errorMessage = 'Invalid username or password!';
      }
    });
}


  navigateToRegister() {
    this.router.navigateByUrl('/register');
  }
}
