package bme.webapp.freelancer.entity.user;

import bme.webapp.freelancer.entity.Job;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends User {
    @ElementCollection
    private List<String> skills;
    @Column
    private Integer experienceLevel;
    @Column
    private Double hourlyRate;

    @ManyToMany(mappedBy = "appliedEmployees")
    private List<Job> appliedJobs;
}
