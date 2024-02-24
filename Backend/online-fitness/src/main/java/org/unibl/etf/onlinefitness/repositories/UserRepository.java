package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity getUserEntityById(Integer id);
}
