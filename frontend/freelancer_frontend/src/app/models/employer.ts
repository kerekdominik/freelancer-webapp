import { Job } from "./job";

export interface Employer {
    id: number;
    username: string;
    password: string;
    introduction: string;
    createdJobs: Job[];
}
