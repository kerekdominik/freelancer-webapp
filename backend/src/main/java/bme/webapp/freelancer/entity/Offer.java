package bme.webapp.freelancer.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "offers")
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="username")
    @JsonIdentityReference(alwaysAsId=true)
    private User employee;

    @Column
    private Double hourlyRate;
    @Column
    private Integer estimatedTime;
    @Column
    private String description;
    @Column
    private Boolean status;
}
