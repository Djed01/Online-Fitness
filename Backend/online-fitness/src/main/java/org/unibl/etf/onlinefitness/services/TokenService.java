package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.entities.TokenEntity;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.TokenRepository;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenEntity getByToken(String token){
        return tokenRepository.findByToken(token);
    }
}
