package bme.webapp.freelancer.repository;

import bme.webapp.freelancer.entity.Role;
import bme.webapp.freelancer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(Role role);

    boolean existsByUsername(String username);
}
