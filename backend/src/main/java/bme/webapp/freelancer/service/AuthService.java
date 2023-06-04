package bme.webapp.freelancer.service;

import bme.webapp.freelancer.dto.AuthResponseDto;
import bme.webapp.freelancer.dto.LoginRequestDto;
import bme.webapp.freelancer.dto.RegisterRequestDto;
import bme.webapp.freelancer.entity.Role;
import bme.webapp.freelancer.entity.User;
import bme.webapp.freelancer.repository.UserRepository;
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

    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(Role.valueOf(registerRequestDto.getRole()));
        if (user.getRole() == Role.EMPLOYEE) {
            user.setSkills(List.of(registerRequestDto.getSkills().split(",")));
            user.setExperienceLevel(registerRequestDto.getExperienceLevel());
            user.setHourlyPrice(Double.parseDouble(String.valueOf(registerRequestDto.getHourlyPrice())));

            user.setIntroduction("-");
        } else {
            user.setIntroduction(registerRequestDto.getIntroduction());

            user.setSkills(List.of("-"));
            user.setExperienceLevel(-1);
            user.setHourlyPrice(-1.0);
        }

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
                )
        );
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
