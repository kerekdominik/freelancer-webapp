package bme.webapp.freelancer.repository;

import bme.webapp.freelancer.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
public interface JobRepository extends JpaRepository<Job, Integer> {
}