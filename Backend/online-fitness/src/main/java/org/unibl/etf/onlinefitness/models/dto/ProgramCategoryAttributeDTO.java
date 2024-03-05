package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;
import org.w3c.dom.Attr;

@Data
public class ProgramCategoryAttributeDTO {
    private Integer id;
    private Integer programId;
    private AttributeDTO attribute;
}
