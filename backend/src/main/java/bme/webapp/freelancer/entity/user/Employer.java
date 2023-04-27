package bme.webapp.freelancer.entity.user;

import bme.webapp.freelancer.entity.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "employers")
@NoArgsConstructor
@AllArgsConstructor
public class Employer extends User{
    @Column
    private String introduction;

    @OneToMany(mappedBy = "employer")
    private List<Job> createdJobs;
}
