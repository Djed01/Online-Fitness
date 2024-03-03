package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "program_has_categoryattribute", schema = "fitness", catalog = "")
public class ProgramCategoryAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProgramID", referencedColumnName = "ID", nullable = false)
    private ProgramEntity program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryAttributeID", referencedColumnName = "ID", nullable = false)
    private CategoryAttributeEntity attribute;

}
