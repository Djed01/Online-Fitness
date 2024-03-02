package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

@Data
public class TokenDTO {
    private Integer id;
    private String token;
    private Integer userId;
}
