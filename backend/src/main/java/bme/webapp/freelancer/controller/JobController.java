package bme.webapp.freelancer.controller;

import bme.webapp.freelancer.dto.JobDto;
import bme.webapp.freelancer.entity.Category;
import bme.webapp.freelancer.entity.Job;
import bme.webapp.freelancer.entity.User;
import bme.webapp.freelancer.repository.CategoryRepository;
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
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Job>> getJobs() {
        return new ResponseEntity<>(jobRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer id) {
        Optional<Job> optJob = jobRepository.findById(id);
        return optJob
                .map(job -> new ResponseEntity<>(job, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/myjobs")
    public ResponseEntity<List<Job>> getCurrentUserJobs() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Optional<User> optUser = userRepository.findByUsername(username);

            if (optUser.isPresent()) {
                User user = optUser.get();
                List<Job> userJobs = jobRepository.findByEmployer(user);
                return new ResponseEntity<>(userJobs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody JobDto jobDto) {

        Job job = new Job();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setPrice(jobDto.getPrice());
        job.setExperienceLevel(jobDto.getExperienceLevel());

        String categoryName = jobDto.getCategory();
        Optional<Category> optCategory = categoryRepository.findByName(categoryName);
        if (optCategory.isEmpty()) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
        Category category = optCategory.get();
        job.setCategory(category);


        String employerUsername = jobDto.getEmployer();
        Optional<User> optEmployer = userRepository.findByUsername(employerUsername);
        if (optEmployer.isEmpty()) {
            return new ResponseEntity<>("Employer not found", HttpStatus.NOT_FOUND);
        }
        User employer = optEmployer.get();
        job.setEmployer(employer);

        jobRepository.save(job);

        return new ResponseEntity<>("Job saved to database!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Integer id, @RequestBody JobDto jobDto) {
        Optional<Job> optJob = jobRepository.findById(id);

        if (optJob.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Job job = optJob.get();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setPrice(jobDto.getPrice());
        job.setExperienceLevel(jobDto.getExperienceLevel());

        String categoryName = jobDto.getCategory();
        Optional<Category> optCategory = categoryRepository.findByName(categoryName);
        if (optCategory.isEmpty()) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
        Category category = optCategory.get();
        job.setCategory(category);


        String employerUsername = jobDto.getEmployer();
        Optional<User> optEmployer = userRepository.findByUsername(employerUsername);
        if (optEmployer.isEmpty()) {
            return new ResponseEntity<>("Employer not found", HttpStatus.NOT_FOUND);
        }
        User employer = optEmployer.get();
        job.setEmployer(employer);


        jobRepository.save(job);

        return new ResponseEntity<>("Job updated!", HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        Optional<Job> optJob = jobRepository.findById(id);

        if (optJob.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        jobRepository.delete(optJob.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
