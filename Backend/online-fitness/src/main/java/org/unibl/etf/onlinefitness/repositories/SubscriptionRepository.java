package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.SubscriptionEntity;

import java.util.List;


public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity,Integer> {
    List<SubscriptionEntity> findAllByUserId(Integer id);
    SubscriptionEntity findByCategoryIdAndUserId(Integer categoryId,Integer userId);
}
