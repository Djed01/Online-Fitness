package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity,Integer> {
    TokenEntity findByToken(String token);
    TokenEntity findByUserId(Integer id);
}
