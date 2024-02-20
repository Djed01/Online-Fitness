package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Entity
@Table(name = "question", schema = "fitness", catalog = "")
public class QuestionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Content")
    private String content;
    @Basic
    @Column(name = "Date")
    private Date date;
    @Basic
    @Column(name = "Seen")
    private Boolean seen;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "ProgramID", referencedColumnName = "ID", nullable = false)
    private ProgramEntity program;

}
