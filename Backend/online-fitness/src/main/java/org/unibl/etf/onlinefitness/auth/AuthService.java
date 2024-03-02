package org.unibl.etf.onlinefitness.auth;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.auth.dto.LoginRequestDTO;
import org.unibl.etf.onlinefitness.auth.dto.SignUpRequestDTO;
import org.unibl.etf.onlinefitness.auth.dto.TokenDTO;
import org.unibl.etf.onlinefitness.config.JwtService;
import org.unibl.etf.onlinefitness.exceptions.InvalidUsernameException;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.dto.UserDTO;
import org.unibl.etf.onlinefitness.models.entities.TokenEntity;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.TokenRepository;
import org.unibl.etf.onlinefitness.repositories.UserRepository;
import org.unibl.etf.onlinefitness.services.EmailService;
import org.unibl.etf.onlinefitness.services.UserService;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public void signup(SignUpRequestDTO signUpRequest) {
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
        userRepository.save(user);
        TokenEntity tokenEntity = generateToken(user);
        tokenRepository.save(tokenEntity);
        emailService.sendActivationEmail(user.getEmail(),tokenEntity.getToken());
    }

    public TokenDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidUsernameException("Invalid username or password."));
        var jwt = jwtService.generateToken(user);
        return TokenDTO.builder().token(jwt).build();
    }

    public void activateUser(String token){
        TokenEntity tokenEntity = tokenRepository.findByToken(token);
        userService.activateUser(tokenEntity.getUser().getId());
    }

    private TokenEntity generateToken(UserEntity userEntity){
        UUID uuid = UUID.randomUUID();
        // Converting UUID to String and removing hyphens
        String token = uuid.toString().replaceAll("-", "");
        return TokenEntity.builder().token(token).user(userEntity).build();
    }


}
