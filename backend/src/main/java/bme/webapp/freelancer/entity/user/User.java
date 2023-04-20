package bme.webapp.freelancer.entity.user;

import bme.webapp.freelancer.entity.Roles;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;
}
