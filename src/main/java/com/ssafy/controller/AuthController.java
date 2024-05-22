package com.ssafy.controller;

import com.ssafy.config.JwtTokenProvider;
import com.ssafy.dao.user.UserDao;
import com.ssafy.dto.auth.*;
import com.ssafy.dto.realestate.UpdateRealtorInfoRequest;
import com.ssafy.dto.user.User;
import com.ssafy.service.auth.AuthService;
import com.ssafy.service.user.RealtorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final RealtorService realtorService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.login(loginRequest);
        if (tokenResponse != null) {
            return ResponseEntity.ok(tokenResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/realtor-signup")
    public ResponseEntity<TokenResponse> realtorSignUp(@RequestBody RealtorSignUpRequest signUpRequest) {
        TokenResponse tokenResponse = authService.realtorSignUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
    }

    @PostMapping("/password-reset")
    public ResponseEntity<Void> resetPassword(@RequestBody PasswordResetRequest request) {
        realtorService.resetPassword(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-info")
    public ResponseEntity<Void> updateRealtorInfo(@RequestBody UpdateRealtorInfoRequest updateRequest) {
        authService.updateRealtorInfo(updateRequest);
        return ResponseEntity.ok().build();
    }
}