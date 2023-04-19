package bme.webapp.freelancer.repository.user;

import bme.webapp.freelancer.entity.user.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
