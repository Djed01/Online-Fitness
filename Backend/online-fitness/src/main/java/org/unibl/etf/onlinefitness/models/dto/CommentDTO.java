package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CommentDTO {
    private Integer id;
    private String content;
    private Date date;
    private String userUsername;
    private String programId;
}
