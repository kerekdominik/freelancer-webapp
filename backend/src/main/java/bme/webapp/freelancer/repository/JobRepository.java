package bme.webapp.freelancer.repository;

import bme.webapp.freelancer.entity.Job;
import bme.webapp.freelancer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByEmployer(User employer);
}