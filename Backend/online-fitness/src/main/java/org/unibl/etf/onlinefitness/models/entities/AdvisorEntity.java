package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "advisor", schema = "fitness", catalog = "")
public class AdvisorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Username")
    private String username;
    @Basic
    @Column(name = "PasswordHash")
    private String passwordHash;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Surname")
    private String surname;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Status")
    private Boolean status;

}
