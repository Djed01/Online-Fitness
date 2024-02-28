package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;

@Data
public class ImageDTO {
    private Integer id;
    private Integer programId;
    private String name;
    private String type;
    private Long size;
    private byte[]data;
}
