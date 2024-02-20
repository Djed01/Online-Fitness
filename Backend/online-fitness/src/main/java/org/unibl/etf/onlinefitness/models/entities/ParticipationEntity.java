package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Entity
@Table(name = "participation", schema = "fitness", catalog = "")
public class ParticipationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "ProgramID", referencedColumnName = "ID", nullable = false)
    private ProgramEntity program;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;

}
