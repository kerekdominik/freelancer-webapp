package bme.webapp.freelancer.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Job {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;
    private String title;
    private String description;
    private Integer price;

    //@OneToMany(mappedBy = "job")
    //private List<JobApplication> jobApplications;

    @ManyToOne
    private Category category;

}
