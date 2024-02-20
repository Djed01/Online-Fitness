package org.unibl.etf.onlinefitness.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "subscription", schema = "fitness", catalog = "")
public class SubscriptionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "CategoryID", referencedColumnName = "ID", nullable = false)
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;

}
