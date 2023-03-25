package bme.webapp.freelancer.repository;

import bme.webapp.freelancer.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}