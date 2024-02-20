package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "program", schema = "fitness", catalog = "")
public class ProgramEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "Price")
    private Integer price;
    @Basic
    @Column(name = "Difficulty")
    private String difficulty;
    @Basic
    @Column(name = "Duration")
    private Integer duration;
    @Basic
    @Column(name = "Location")
    private String location;
    @Basic
    @Column(name = "InstructorName")
    private String instructorName;
    @Basic
    @Column(name = "InstructorSurname")
    private String instructorSurname;
    @Basic
    @Column(name = "InstructorContact")
    private String instructorContact;
    @Basic
    @Column(name = "Status")
    private Boolean status;
    @Basic
    @Column(name = "CreationDate")
    private Date creationDate;
    @Basic
    @Column(name = "Link")
    private String link;
    @OneToMany(mappedBy = "program")
    private List<ImageEntity> images;
    @ManyToOne
    @JoinColumn(name = "CategoryID", referencedColumnName = "ID", nullable = false)
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;
    @OneToMany(mappedBy = "program")
    private List<ProgramCategoryAttributeEntity> attributes;

}
