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
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.UserRepository;

@Service
@RequiredArgsConstructor
<<<<<<< HEAD
public class UserService implements UserDetailsService {
=======
public class UserService implements UserDetailsService{
>>>>>>> a9a1ba91a9db61eed7dc0519ea1c19585a03c409
    @Autowired
    UserRepository userRepository;

    private final ModelMapper modelMapper;


    public UserDTO getUserByUserId(Integer id){
        UserEntity entity = userRepository.getUserEntityById(id);
        return modelMapper.map(entity, UserDTO.class);
    }

    public UserEntity addUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException("Ne postoji korisnik."));
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        return modelMapper.map(this.userRepository.findByUsername(username), UserEntity.class);
    }

}
