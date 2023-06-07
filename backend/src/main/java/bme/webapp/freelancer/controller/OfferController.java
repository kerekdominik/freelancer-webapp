package bme.webapp.freelancer.controller;

import bme.webapp.freelancer.dto.OfferDto;
import bme.webapp.freelancer.entity.Job;
import bme.webapp.freelancer.entity.Offer;
import bme.webapp.freelancer.entity.User;
import bme.webapp.freelancer.repository.JobRepository;
import bme.webapp.freelancer.repository.OfferRepository;
import bme.webapp.freelancer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public ResponseEntity<List<Offer>> getOffers() {
        return new ResponseEntity<>(offerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Integer id) {
        Optional<Offer> optOffer = offerRepository.findById(id);
        return optOffer
                .map(offer -> new ResponseEntity<>(offer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createOffer(@RequestBody OfferDto offerDto) {

        Offer offer = new Offer();
        offer.setHourlyRate(offerDto.getHourlyRate());
        offer.setEstimatedTime(offerDto.getEstimatedTime());
        offer.setDescription(offerDto.getDescription());
        offer.setStatus(offerDto.getStatus());

        Integer jobId = offerDto.getJobId();
        Optional<Job> optJob = jobRepository.findById(jobId);
        if (optJob.isEmpty()) {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }
        Job job = optJob.get();
        offer.setJob(job);

        String employeeUsername = offerDto.getEmployeeUsername();
        Optional<User> optEmployee = userRepository.findByUsername(employeeUsername);
        if (optEmployee.isEmpty()) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        User employee = optEmployee.get();
        employee.getAppliedJobs().add(job);
        job.getAppliedEmployees().add(employee);
        offer.setEmployee(employee);

        offerRepository.save(offer);

        return new ResponseEntity<>("Offer saved to database!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOffer(@PathVariable Integer id, @RequestBody OfferDto offerDto) {
        Optional<Offer> optOffer = offerRepository.findById(id);

        if (optOffer.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Offer offer = optOffer.get();
        offer.setHourlyRate(offerDto.getHourlyRate());
        offer.setEstimatedTime(offerDto.getEstimatedTime());
        offer.setDescription(offerDto.getDescription());
        offer.setStatus(offerDto.getStatus());

        Integer jobId = offerDto.getJobId();
        Optional<Job> optJob = jobRepository.findById(jobId);
        if (optJob.isEmpty()) {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }
        Job job = optJob.get();
        offer.setJob(job);

        String employeeUsername = offerDto.getEmployeeUsername();
        Optional<User> optEmployee = userRepository.findByUsername(employeeUsername);
        if (optEmployee.isEmpty()) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        User employee = optEmployee.get();
        offer.setEmployee(employee);

        offerRepository.save(offer);

        return new ResponseEntity<>("Offer updated!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) {
        Optional<Offer> optOffer = offerRepository.findById(id);

        if (optOffer.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        offerRepository.delete(optOffer.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
