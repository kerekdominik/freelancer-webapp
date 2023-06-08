import { Job } from "./job.model";


export interface User {
    id: number;
    username: string;
    role: string;
    introduction: string;
    createdJobs: Job[];
    skills: string[];
    experienceLevel: number;
    hourlyPrice: number;
    appliedJobs: Job[];
}
