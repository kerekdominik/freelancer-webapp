package bme.webapp.freelancer.controller;

import bme.webapp.freelancer.dto.LoginDto;
import bme.webapp.freelancer.dto.RegisterDto;
import bme.webapp.freelancer.entity.user.Roles;
import bme.webapp.freelancer.entity.user.Employee;
import bme.webapp.freelancer.entity.user.Employer;
import bme.webapp.freelancer.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
         if (!userRepository.existsByUsername(loginDto.getUsername()))
            return ResponseEntity.badRequest().body("Username not found!");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login successful!");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername()))
            return ResponseEntity.badRequest().body("Username is already taken!");

        // TODO: check what type of user is being registered
        if (registerDto.getRole() == Roles.ROLE_EMPLOYEE) {
            Employee user = new Employee();

            user.setRole(Roles.ROLE_EMPLOYEE);

            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            user.setExperienceLevel(registerDto.getExperienceLevel());

            userRepository.save(user);
        } else if (registerDto.getRole() == Roles.ROLE_EMPLOYER) {
            Employer user = new Employer();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setRole(Roles.ROLE_EMPLOYER);
            userRepository.save(user);
        } else {
            return ResponseEntity.badRequest().body("Invalid role!");
        }

        return ResponseEntity.ok("User registered successfully!");
    }
}
