package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.AvatarEntity;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<AvatarEntity,Integer> {
}
