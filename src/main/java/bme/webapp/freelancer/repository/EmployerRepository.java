package bme.webapp.freelancer.repository;

import bme.webapp.freelancer.model.user.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
