package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.dto.UserDTO;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity getUserEntityById(Integer id);
    Optional<UserEntity> findByUsername(String username);

}
