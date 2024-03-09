package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "message", schema = "fitness", catalog = "")
public class MessageEntity {
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
    @ManyToOne
    @JoinColumn(name = "Sender", referencedColumnName = "ID", nullable = false)
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "Receiver", referencedColumnName = "ID", nullable = false)
    private UserEntity receiver;

}
