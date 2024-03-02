package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "token", schema = "fitness", catalog = "")
public class TokenEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Token")
    private String token;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;

    public TokenEntity() {

    }

    public TokenEntity(Integer id,String token, UserEntity user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }
}
