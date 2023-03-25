package bme.webapp.freelancer.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employers")
public class Employer extends User{
    @Column
    private String description;
    //@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    //private List<Job> cratedJobs;
}
