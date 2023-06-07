import { Category } from "./category.model";
import { User } from "./user.model";

export interface Job {
    id: number;
    title: string;
    category: string;
    description: string;
    skills: string[];
    experienceLevel: number;
    price: number;
    appliedEmployees: User[];
} 