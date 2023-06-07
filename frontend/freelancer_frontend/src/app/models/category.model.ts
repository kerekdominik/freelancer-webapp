import { Job } from "./job.model";

export interface Category {
    id: number;
    name: string;
    jobs: Job[];
}