
export interface Offer {
    id: number;
    employeeId: number;
    jobId: number;
    hourlyRate: number;
    estimatedTime: number;
    description: string;
    status: boolean;
}