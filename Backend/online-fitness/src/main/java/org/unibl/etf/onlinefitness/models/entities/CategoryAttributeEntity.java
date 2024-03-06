package org.unibl.etf.onlinefitness.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "categoryattribute", schema = "fitness", catalog = "")
public class CategoryAttributeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Status")
    private Boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CategoryID", referencedColumnName = "ID", nullable = false)
    private CategoryEntity category;

    @JsonIgnore
    @ManyToMany(mappedBy = "attributes")
    List<ProgramEntity> programs;

}
