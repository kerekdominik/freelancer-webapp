package bme.webapp.freelancer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Job> jobs;
}
