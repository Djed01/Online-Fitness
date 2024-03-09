package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

@Data
public class MessageRequestDTO {
    private String content;
    private Integer senderId;
    private Integer receiverId;
}
