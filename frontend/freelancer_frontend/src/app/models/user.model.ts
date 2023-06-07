import { Job } from "./job.model";
import { Roles } from "./roles";


export interface User {
    id: number;
    usernames: string;
    role: string;
    skills: string[];
    experienceLevel: number;
    hourlyPrice: number;
    appliedJobs: Job[];
    introduction: string;
    createdJobs: Job[];
}
