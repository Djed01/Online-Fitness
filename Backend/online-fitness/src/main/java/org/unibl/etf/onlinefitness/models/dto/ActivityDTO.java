package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityDTO {
    private Integer id;
    private String name;
    private Integer sets;
    private Integer reps;
    private Integer weight;
    private Date date;
}
