package in.vit.security.jwt.controllers;

import in.vit.security.jwt.exception.TokenRefreshException;
import in.vit.security.jwt.models.RefreshToken;
import in.vit.security.jwt.models.User;
import in.vit.security.jwt.payload.request.LoginRequest;
import in.vit.security.jwt.payload.request.SignupRequest;
import in.vit.security.jwt.payload.request.TokenRefreshRequest;
import in.vit.security.jwt.payload.response.JwtResponse;
import in.vit.security.jwt.payload.response.TokenRefreshResponse;
import in.vit.security.jwt.security.jwt.JwtUtils;
import in.vit.security.jwt.security.security.RefreshTokenService;
import in.vit.security.jwt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/sign-in")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String jwt = jwtUtils.generateToken(user);

        List<String> roles = (user.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList()));

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId(),user);

        return new JwtResponse(
            jwt,
            refreshToken.getToken(),
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            roles
        );
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User user = userService.registerCustomerSignUp(signUpRequest);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/refresh-token")
    public TokenRefreshResponse refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshToken -> {
                refreshTokenService.verifyExpiration(refreshToken); // Verify expiration
                String token = jwtUtils.generateToken(refreshToken.getUser());
                refreshTokenService.expireToken(refreshToken); // Save the updated entity
                return new TokenRefreshResponse(token, requestRefreshToken);
            })
            .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                    "Refresh token is not matched!")
        );
    }
}