package bme.webapp.freelancer.repository;

import bme.webapp.freelancer.model.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}