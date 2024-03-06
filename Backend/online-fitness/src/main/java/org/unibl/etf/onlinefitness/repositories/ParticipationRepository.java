package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.ParticipationEntity;

public interface ParticipationRepository extends JpaRepository<ParticipationEntity,Integer> {
    ParticipationEntity findByProgramIdAndUserId(Integer programId, Integer userId);
}
