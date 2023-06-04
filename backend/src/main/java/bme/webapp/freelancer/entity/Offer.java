package bme.webapp.freelancer.entity;

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
    private User employeeId;

    @Column
    private Double hourlyRate;
    @Column
    private Integer estimatedTime;
    @Column
    private String description;
    @Column
    private Boolean status;
}
