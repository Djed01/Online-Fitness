package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.CommentEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
    List<CommentEntity> findAllByProgramId(Integer id);
}
