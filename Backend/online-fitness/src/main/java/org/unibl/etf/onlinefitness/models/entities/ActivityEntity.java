package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Entity
@Table(name = "activity", schema = "fitness", catalog = "")
public class ActivityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Sets")
    private Integer sets;
    @Basic
    @Column(name = "Reps")
    private Integer reps;
    @Basic
    @Column(name = "Weight")
    private Integer weight;
    @Basic
    @Column(name = "Status")
    private Boolean status;
    @Basic
    @Column(name = "Date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;

}
