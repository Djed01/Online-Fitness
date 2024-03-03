package org.unibl.etf.onlinefitness.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDTO {
    private Integer id;
    private Integer programId;
    private String name;
    private String type;
    private Long size;
    private byte[]data;

    public ImageDTO(){}
    public ImageDTO(Integer id, Integer programId, String name, String type, Long size, byte[] data) {
        this.id = id;
        this.programId = programId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.data = data;
    }
}
