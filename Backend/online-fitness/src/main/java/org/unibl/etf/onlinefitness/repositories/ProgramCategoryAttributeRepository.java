package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.ProgramCategoryAttributeEntity;

public interface ProgramCategoryAttributeRepository extends JpaRepository<ProgramCategoryAttributeEntity, Integer>  {
}
