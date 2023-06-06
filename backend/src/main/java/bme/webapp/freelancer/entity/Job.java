package bme.webapp.freelancer.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="name")
    @JsonIdentityReference(alwaysAsId=true)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="username")
    @JsonIdentityReference(alwaysAsId=true)
    @JoinTable(name = "employee_job",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<User> appliedEmployees;

    @ManyToOne
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="username")
    @JsonIdentityReference(alwaysAsId=true)
    @JoinColumn(name = "employer_id")
    private User employer;
}
