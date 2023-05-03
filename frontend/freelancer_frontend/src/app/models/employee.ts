import { Job } from "./job";


export interface Employee {
    id: number;
    usernames: string;
    password: string;
    skills: string[];
    experienceLevel: number;
    hourlyRate: number;
    appliedJobs: Job[];
}
