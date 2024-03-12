package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.ActivityEntity;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity,Integer> {
    public List<ActivityEntity> findAllByUserIdAndStatus(Integer userId,Boolean status);
}
