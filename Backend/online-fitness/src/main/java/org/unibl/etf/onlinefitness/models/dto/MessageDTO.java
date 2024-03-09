package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

import java.util.Date;


@Data
public class MessageDTO {
    private Integer id;
    private String content;
    private Date date;
    private UserInfoDTO sender;
    private UserInfoDTO receiver;
}
