package org.unibl.etf.onlinefitness.auth.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String name;
    private String surname;
    private String city;
    private String username;
    private String password;
    private String email;

}
