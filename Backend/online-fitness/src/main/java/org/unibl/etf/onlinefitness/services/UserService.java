package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.dto.UserDTO;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    private final ModelMapper modelMapper;


    public UserDTO getUserByUserId(Integer id){
        UserEntity entity = userRepository.getUserEntityById(id);
        return modelMapper.map(entity, UserDTO.class);
    }

    public UserDTO addUser(UserDTO userDTO){
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        return modelMapper.map(this.userRepository.findByUsername(username), UserEntity.class);
    }

}
