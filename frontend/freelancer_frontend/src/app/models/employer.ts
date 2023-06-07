import { Job } from "./job.model";

export interface Employer {
    id: number;
    username: string;
    password: string;
    introduction: string;
    createdJobs: Job[];
}
