package bme.webapp.freelancer.dto;

import lombok.Data;

@Data
public class JobDto {

    String title;
    String description;
    Double price;
    String category;
    String employer;

}
