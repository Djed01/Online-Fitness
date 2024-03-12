package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.BodyWeightEntity;

import java.util.List;

public interface BodyWeightRepository extends JpaRepository<BodyWeightEntity,Integer> {
    public List<BodyWeightEntity> findAllByUserId(Integer id);
}
