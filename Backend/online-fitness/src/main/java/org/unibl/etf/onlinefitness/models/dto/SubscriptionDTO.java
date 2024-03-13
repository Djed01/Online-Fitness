package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class SubscriptionDTO {
    private Integer id;
    private String category;
    private Boolean subscribed;
}
