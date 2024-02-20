package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Entity
@Table(name = "log", schema = "fitness", catalog = "")
public class LogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "Date")
    private Date date;
    @Basic
    @Column(name = "Type")
    private String type;

}
