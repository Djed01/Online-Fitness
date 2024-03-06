package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ParticipationDTO {
    private Integer id;
    private Date date;
    private Integer programId;
    private Integer userId;
}
