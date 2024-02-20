package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "avatar", schema = "fitness", catalog = "")
public class AvatarEntity {
    @Basic
    @Column(name = "URL")
    private String url;

    @Id
    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;

}
