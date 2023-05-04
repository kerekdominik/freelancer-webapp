package bme.webapp.freelancer.entity;

import bme.webapp.freelancer.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<User> appliedEmployees;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User employer;
}
