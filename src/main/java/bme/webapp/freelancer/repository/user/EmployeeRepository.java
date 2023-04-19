package bme.webapp.freelancer.repository.user;

import bme.webapp.freelancer.entity.user.User;

import java.util.Optional;

public interface EmployeeRepository extends UserRepository {
    Optional<User> findByUsername(String username);
}