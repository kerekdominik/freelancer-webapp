package bme.webapp.freelancer.entity.user;

import bme.webapp.freelancer.entity.Job;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee extends User {
    @ElementCollection
    private List<String> skills;
    @Column
    private Integer experienceLevel;
    @Column
    private Double hourlyRate;
    @ManyToMany
    @JoinTable(
            name = "employee_applied_jobs",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<Job> appliedJobs;
}
