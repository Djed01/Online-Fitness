package org.unibl.etf.onlinefitness.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.auth.dto.ChangePasswordDTO;
import org.unibl.etf.onlinefitness.auth.dto.LoginRequestDTO;
import org.unibl.etf.onlinefitness.auth.dto.SignUpRequestDTO;
import org.unibl.etf.onlinefitness.exceptions.InvalidUsernameException;
import org.unibl.etf.onlinefitness.exceptions.NotActivatedException;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.models.enumeration.LogType;
import org.unibl.etf.onlinefitness.services.LogService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final LogService logService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    public UserEntity signup(@RequestBody SignUpRequestDTO signUpRequest) {
         return authService.signup(signUpRequest);
    }


    @GetMapping("/activate")
    public void activateAccount(@RequestParam("token") String token) {
        authService.activateUser(token);
    }

    @PostMapping("/regenerate")
    public ResponseEntity<?> regenerateActivationLink(@RequestBody LoginRequestDTO request){
        try {
            return ResponseEntity.ok(authService.regenerateActivationLink(request));
        }catch (BadCredentialsException e){
            logService.log(LogType.WARN,"Invalid username or password!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
        }catch (Exception e) {
            logService.log(LogType.ERROR,"An unexpected error occurred while regenerating activation link!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request){
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (NotActivatedException e) {
            logService.log(LogType.INFO,"Someone tried to login with not activated account!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account not activated!");
        } catch (BadCredentialsException e) {
            logService.log(LogType.INFO,"Someone tried to login with invalid username or password!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
        } catch (Exception e) {
            logService.log(LogType.ERROR,"An unexpected error occurred while logging.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }

    }

    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        try {
            authService.changePassword(changePasswordDTO);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (RuntimeException e){
            logService.log(LogType.WARN,"Invalid password provided while changing the password!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password!");
        }
    }
}
