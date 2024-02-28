package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avatar", schema = "fitness", catalog = "")
public class AvatarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    private UserEntity user;

    @Column(name="Name",nullable = false)
    private String name;
    @Basic

    @Column(name="Type",nullable = false)
    private String type;
    @Basic

    @Column(name="Size",nullable = false)
    private Long size;

}
