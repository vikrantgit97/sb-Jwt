package com.spring.security.jwt.controllers;

import com.spring.security.jwt.exception.TokenRefreshException;
import com.spring.security.jwt.models.RefreshToken;
import com.spring.security.jwt.models.User;
import com.spring.security.jwt.payload.request.LoginRequest;
import com.spring.security.jwt.payload.request.SignupRequest;
import com.spring.security.jwt.payload.request.TokenRefreshRequest;
import com.spring.security.jwt.payload.response.JwtResponse;
import com.spring.security.jwt.payload.response.TokenRefreshResponse;
import com.spring.security.jwt.security.jwt.JwtUtils;
import com.spring.security.jwt.security.security.RefreshTokenService;
import com.spring.security.jwt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Object principal = authentication.getPrincipal();

        String jwt = jwtUtils.generateToken((UserDetails) principal);

        String s = jwtUtils.extractUsername(jwt);

        User byUsername = userService.findByUsername(s);
        List<String> roles = ((UserDetails) principal).getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(byUsername.getId());

        return ResponseEntity.ok(new JwtResponse
                (
                        jwt,
                        refreshToken.getToken(),
                        byUsername.getId(),
                        byUsername.getUsername(),
                        byUsername.getEmail(),
                        roles));
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User user = userService.registerCustomerSignUp(signUpRequest);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateToken(user);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}