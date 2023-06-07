import { Category } from "./category.model";
import { Employee } from "./employee";

export interface Job {
    id: number;
    title: string;
    category: string;
    description: string;
    skills: string[];
    experienceLevel: number;
    price: number;
    appliedEmployees: Employee[];
} 