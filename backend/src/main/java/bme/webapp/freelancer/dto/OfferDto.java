package bme.webapp.freelancer.dto;

import lombok.Data;

@Data
public class OfferDto {
    private Integer jobId;
    private String employeeUsername;
    private Double hourlyRate;
    private Integer estimatedTime;
    private String description;
    private Boolean status;
}
