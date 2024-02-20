package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Entity
@Table(name = "comment", schema = "fitness", catalog = "")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Comment")
    private String content;
    @Basic
    @Column(name = "Date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "ProgramID", referencedColumnName = "ID", nullable = false)
    private ProgramEntity program;

}
