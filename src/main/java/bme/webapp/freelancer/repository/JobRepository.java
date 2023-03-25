package bme.webapp.freelancer.repository;

import bme.webapp.freelancer.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}