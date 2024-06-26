import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/auth/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { OfferComponent } from './components/offer/offer.component';
import { RegistrationComponent } from './components/auth/registration/registration.component';
import { AuthGuardService } from './services/auth-services/auth.guard';
import { MyJobsComponent } from './components/myjobs/myjobs.component';
import { AddJobComponent } from './components/addjob/addjob.component';
import { EditJobComponent } from './components/editjob/editjob.component';
import { AppliersComponent } from './components/appliers/appliers.component';
import { EmployersComponent } from './components/employers/employers.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [() => inject(AuthGuardService).canActivate()] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'offer-form/:jobId', component: OfferComponent },
  { path: 'myjobs', component: MyJobsComponent },
  { path: 'myjobs/addjob', component: AddJobComponent },
  { path: 'employers', component: EmployersComponent },
  { path: 'myjobs/editjob', component: EditJobComponent },
  { path: 'myjobs/:jobId/appliers', component: AppliersComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
