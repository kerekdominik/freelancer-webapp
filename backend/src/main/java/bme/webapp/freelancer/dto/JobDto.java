package bme.webapp.freelancer.dto;

import lombok.Data;

@Data
public class JobDto {

    String title;
    String description;
    Integer price;
    String category;
    String employer;

}
