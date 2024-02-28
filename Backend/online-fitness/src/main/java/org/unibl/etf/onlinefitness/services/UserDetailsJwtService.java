package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsJwtService implements UserDetailsService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        return modelMapper.map(this.userRepository.findByUsername(username), UserEntity.class);
    }
}
