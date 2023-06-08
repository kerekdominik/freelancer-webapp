package bme.webapp.freelancer.controller;

import bme.webapp.freelancer.entity.Job;
import bme.webapp.freelancer.entity.Role;
import bme.webapp.freelancer.entity.User;
import bme.webapp.freelancer.repository.JobRepository;
import bme.webapp.freelancer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/profile")
    @CrossOrigin
    public ResponseEntity<User> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Optional<User> optUser = userRepository.findByUsername(username);
            return optUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<User>> getAllEmployees() {
        List<User> employees = userRepository.findAllByRole(Role.EMPLOYEE);
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employers")
    public ResponseEntity<List<User>> getAllEmployers() {
        List<User> employers = userRepository.findAllByRole(Role.EMPLOYER);
        if(employers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employers, HttpStatus.OK);
    }

    @GetMapping("/employees/{jobId}")
    public ResponseEntity<List<User>> getEmployeesByJob(@PathVariable Integer jobId) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);
        if(optionalJob.isPresent()){
            Job job = optionalJob.get();
            return ResponseEntity.ok(job.getAppliedEmployees());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}
