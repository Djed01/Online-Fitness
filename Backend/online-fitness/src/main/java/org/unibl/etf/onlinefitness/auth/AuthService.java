package org.unibl.etf.onlinefitness.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.auth.dto.LoginRequestDTO;
import org.unibl.etf.onlinefitness.auth.dto.SignUpRequestDTO;
import org.unibl.etf.onlinefitness.auth.dto.TokenDTO;
import org.unibl.etf.onlinefitness.config.JwtService;
import org.unibl.etf.onlinefitness.exceptions.InvalidUsernameException;
import org.unibl.etf.onlinefitness.models.dto.UserDTO;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.UserRepository;
import org.unibl.etf.onlinefitness.services.UserService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserEntity signup(SignUpRequestDTO signUpRequest) {
        UserEntity  user = new UserEntity ();
        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setCity(signUpRequest.getCity());
        user.setUsername(signUpRequest.getUsername());
        user.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setActivationStatus(false);
        user.setStatus(true);
        user.setRole("Rola");
        return userRepository.save(user);
    }

    public TokenDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidUsernameException("Invalid username or password."));
        var jwt = jwtService.generateToken(user);
        return TokenDTO.builder().token(jwt).build();
    }
}
