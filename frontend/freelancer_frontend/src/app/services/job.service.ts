import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Job } from '../models/job.model';

@Injectable({
  providedIn: 'root',
})
export class JobService {
  private apiURL = 'http://localhost:8080/api/jobs';

  constructor(private http: HttpClient) { }

  getAllJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(this.apiURL);
  }

  getJob(jobId: number): Observable<Job> {
    return this.http.get<Job>(this.apiURL + '/' + jobId);
  }

  getMyJobs(token: string): Observable<Job[]> {
    const headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    return this.http.get<Job[]>(this.apiURL + '/myjobs', { headers: headers });
  }

  createJob(job: Job, token: string): Observable<any> {
    const headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    return this.http.post(`${this.apiURL}`, job, { headers: headers });
  }
  
  updateJob(id: number, job: Job, token: string): Observable<any> {
    const headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    return this.http.put(`http://localhost:8080/api/jobs/${id}`, job, { headers: headers });
  }

  deleteJob(id: number, token: string): Observable<any> {
    const headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    return this.http.delete(`http://localhost:8080/api/jobs/${id}`,  { headers: headers });
  }
  
}
