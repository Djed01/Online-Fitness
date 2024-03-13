package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;

import java.util.Date;
import java.util.List;

public interface ProgramRepository extends JpaRepository<ProgramEntity,Integer> {
    List<ProgramEntity> findAllByStatus(Boolean status);
    List<ProgramEntity> findAllByUserId(Integer id);
    List<ProgramEntity> findByCategoryIdAndCreationDateBetween(Integer categoryId, Date from, Date to);
    ProgramEntity findAllById(Integer id);
}
