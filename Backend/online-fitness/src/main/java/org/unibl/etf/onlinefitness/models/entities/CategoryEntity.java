package org.unibl.etf.onlinefitness.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "category", schema = "fitness", catalog = "")
public class CategoryEntity {
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

    @OneToMany(mappedBy = "category")
    private List<CategoryAttributeEntity> categoryAttributes;

}
