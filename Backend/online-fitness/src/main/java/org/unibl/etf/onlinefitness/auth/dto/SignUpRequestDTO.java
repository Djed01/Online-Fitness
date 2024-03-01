package org.unibl.etf.onlinefitness.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {
    private String name;
    private String surname;
    private String city;
    private String username;
    private String password;
    private String email;

}
