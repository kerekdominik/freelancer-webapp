package bme.webapp.freelancer.auth;

import bme.webapp.freelancer.entity.user.Role;
import bme.webapp.freelancer.entity.user.User;
import bme.webapp.freelancer.repository.user.UserRepository;
import bme.webapp.freelancer.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.valueOf(registerRequest.getRole()));
        if (user.getRole() == Role.EMPLOYEE) {
            user.setSkills(List.of(registerRequest.getSkills().split(",")));
            user.setExperienceLevel(registerRequest.getExperienceLevel());
            user.setHourlyPrice(Double.parseDouble(String.valueOf(registerRequest.getHourlyPrice())));

            user.setIntroduction("-");
        } else {
            user.setIntroduction(registerRequest.getIntroduction());

            user.setSkills(List.of("-"));
            user.setExperienceLevel(-1);
            user.setHourlyPrice(-1.0);
        }

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
                )
        );
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
