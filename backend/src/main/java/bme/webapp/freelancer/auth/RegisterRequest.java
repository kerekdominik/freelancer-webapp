package bme.webapp.freelancer.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
    private String skills;
    private Integer experienceLevel;
    private String introduction;
    private Double hourlyPrice;
}
