package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.exceptions.InvalidUsernameException;
import org.unibl.etf.onlinefitness.models.dto.UserDTO;
import org.unibl.etf.onlinefitness.models.dto.UserInfoDTO;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private final ModelMapper modelMapper;


    public UserDTO getUserByUserId(Integer id){
        UserEntity entity = userRepository.getUserEntityById(id);
        return modelMapper.map(entity, UserDTO.class);
    }

    public UserDTO getUserByUsername(String username){
        Optional<UserEntity> entity = userRepository.findByUsername(username);
        return modelMapper.map(entity, UserDTO.class);
    }

    public Integer getIdByUsername(String username){
        Optional<UserEntity> entity = userRepository.findByUsername(username);
        return entity.get().getId();
    }

    public UserEntity addUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException("Ne postoji korisnik."));
    }


    public void activateUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));

        userEntity.setActivationStatus(true);
        userRepository.save(userEntity);
    }

    public UserEntity updateUserInfo(UserInfoDTO userInfoDTO){
        UserEntity userEntity = userRepository.findByUsername(userInfoDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User with username " + userInfoDTO.getUsername() + " not found"));

        userEntity.setName(userInfoDTO.getName());
        userEntity.setSurname(userInfoDTO.getSurname());
        userEntity.setCity(userInfoDTO.getCity());
        userEntity.setEmail(userInfoDTO.getEmail());

        return userRepository.save(userEntity);
    }
}
