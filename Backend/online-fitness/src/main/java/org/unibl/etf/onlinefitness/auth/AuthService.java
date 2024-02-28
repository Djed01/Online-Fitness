package org.unibl.etf.onlinefitness.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.auth.dto.SignUpRequestDTO;
import org.unibl.etf.onlinefitness.models.dto.UserDTO;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.UserRepository;
import org.unibl.etf.onlinefitness.services.UserService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserDTO signup(SignUpRequestDTO signUpRequest) {
        UserDTO user = new UserDTO();
        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setCity(signUpRequest.getCity());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        return userService.addUser(user);
    }
}
