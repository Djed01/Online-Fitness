package org.unibl.etf.onlinefitness.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.auth.dto.SignUpRequestDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    public void signup(@RequestBody SignUpRequestDTO signUpRequest) {
         authService.signup(signUpRequest);
    }


    @GetMapping("/activate")
    public void activateAccount(@RequestParam("token") String token) {
        authService.activateUser(token);
    }
}
