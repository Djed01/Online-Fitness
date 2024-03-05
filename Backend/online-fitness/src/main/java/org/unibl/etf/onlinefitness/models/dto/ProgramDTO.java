package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ProgramDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private String difficulty;
    private Integer duration;
    private String location;
    private String instructorName;
    private String instructorSurname;
    private String instructorContact;
    private Boolean status;
    private Date creationDate;
    private String link;
    private List<ImageDTO> images;
    private Integer categoryId;
    private String categoryName;
    private Integer userId;
    private List<AttributeDTO> attributes;
}
