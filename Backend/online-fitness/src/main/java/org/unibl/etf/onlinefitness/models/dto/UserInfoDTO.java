package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private String username;
    private String name;
    private String surname;
    private String city;
    private String email;
}
