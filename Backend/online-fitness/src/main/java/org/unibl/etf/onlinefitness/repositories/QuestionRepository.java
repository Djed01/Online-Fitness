package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Integer> {
}
