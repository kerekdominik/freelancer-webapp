package bme.webapp.freelancer.entity;

import bme.webapp.freelancer.entity.user.Employee;
import bme.webapp.freelancer.entity.user.Employer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "employee_job",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> appliedEmployees;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
