package bme.webapp.freelancer.dto;

import bme.webapp.freelancer.entity.Roles;
import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;

    private Roles role;

    private Integer experienceLevel;
}
