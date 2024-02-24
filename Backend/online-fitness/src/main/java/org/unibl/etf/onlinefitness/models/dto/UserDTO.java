package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String name;
    private String surname;
    private String city;
    private String email;
    private Boolean status;
    private Boolean activationStatus;
    private AvatarDTO avatar;
}
