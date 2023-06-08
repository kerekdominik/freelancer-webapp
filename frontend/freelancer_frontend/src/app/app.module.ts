import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegistrationComponent } from './components/auth/registration/registration.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { ProfileComponent } from './components/profile/profile.component';
import { OfferComponent } from './components/offer/offer.component';
import { MyJobsComponent } from './components/myjobs/myjobs.component';
import { AddJobComponent } from './components/addjob/addjob.component';
import { EmployersComponent } from './components/employers/employers.component';
import { EditJobComponent } from './components/editjob/editjob.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent,
    NavbarComponent,
    ProfileComponent,
    OfferComponent,
    MyJobsComponent,
    AddJobComponent,
    EmployersComponent,
    EditJobComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
