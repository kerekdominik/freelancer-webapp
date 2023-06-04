package bme.webapp.freelancer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String username;
    private String password;
    private String role;
    private String skills;
    private Integer experienceLevel;
    private String introduction;
    private Double hourlyPrice;
}
