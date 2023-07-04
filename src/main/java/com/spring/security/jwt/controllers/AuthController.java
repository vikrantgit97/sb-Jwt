package com.spring.security.jwt.controllers;

import com.spring.security.jwt.customresponse.CustomResponse;
import com.spring.security.jwt.exception.CustomerNotFoundException;
import com.spring.security.jwt.exception.TokenRefreshException;
import com.spring.security.jwt.models.Customer;
import com.spring.security.jwt.models.RefreshToken;
import com.spring.security.jwt.payload.request.LoginRequest;
import com.spring.security.jwt.payload.request.SignupRequest;
import com.spring.security.jwt.payload.request.TokenRefreshRequest;
import com.spring.security.jwt.payload.response.JwtResponse;
import com.spring.security.jwt.payload.response.MessageResponse;
import com.spring.security.jwt.payload.response.TokenRefreshResponse;
import com.spring.security.jwt.security.jwt.JwtUtils;
import com.spring.security.jwt.security.services.RefreshTokenService;
import com.spring.security.jwt.security.services.UserDetailsImpl;
import com.spring.security.jwt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private String code;

  private Object data;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private RefreshTokenService refreshTokenService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
            .collect(Collectors.toList());

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),  userDetails.getId(),
            userDetails.getUsername(), userDetails.getEmail(), roles));
  }


  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    try {
      Customer customer = customerService.registerCustomerSignUp(signUpRequest);
      data = "customer registered sucessfully";
      code = "CREATED";
    } catch (CustomerNotFoundException customerNotFoundException) {
      code = "DATA_NOT_CREATED";data =null;
    } 	catch (RuntimeException runtimeException) {
      code = "RUNTIME_EXCEPTION";data =null;
    } catch (Exception exception) {
      code = "EXCEPTION";data =null;
    } finally {
      return CustomResponse.response(code, data);
    }
  }


  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();
    return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
            .map(RefreshToken::getUser)
            .map(user -> {
              String token = jwtUtils.generateTokenFromUsername(user.getUsername());
              return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
            })
            .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                    "Refresh token is not in database!"));
  }


  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = userDetails.getId();
    refreshTokenService.deleteByUserId(userId);
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }
}
