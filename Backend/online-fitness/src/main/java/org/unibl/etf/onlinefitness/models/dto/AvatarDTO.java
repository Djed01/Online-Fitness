package org.unibl.etf.onlinefitness.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvatarDTO {
        private Integer id;
        private Integer userId;
        private String name;
        private String type;
        private Long size;
        private byte[]data;

        // No-argument constructor
        public AvatarDTO() {
        }

        // Constructor with arguments
        public AvatarDTO(Integer id, Integer userId, String name, String type, Long size, byte[] data) {
                this.id = id;
                this.userId = userId;
                this.name = name;
                this.type = type;
                this.size = size;
                this.data = data;
        }
}
