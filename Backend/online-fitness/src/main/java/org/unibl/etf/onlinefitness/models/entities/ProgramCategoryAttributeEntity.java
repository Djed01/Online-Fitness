package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "program_has_categoryattribute", schema = "fitness", catalog = "")
public class ProgramCategoryAttributeEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProgramID", referencedColumnName = "ID", nullable = false)
    private ProgramEntity program;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryAttributeID", referencedColumnName = "ID", nullable = false)
    private CategoryAttributeEntity attribute;

}
