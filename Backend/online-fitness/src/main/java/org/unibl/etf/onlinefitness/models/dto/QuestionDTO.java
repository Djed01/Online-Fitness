package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;
import org.apache.catalina.User;

import java.sql.Date;


@Data
public class QuestionDTO {
    private String content;
    private Date date;
    private Boolean seen;
    private Integer userId;
    private Integer programId;
}
