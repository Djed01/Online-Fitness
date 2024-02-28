package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

@Data
public class AvatarDTO {
        private Integer id;
        private Integer userId;
        private String name;
        private String type;
        private Long size;
        private byte[]data;
}
