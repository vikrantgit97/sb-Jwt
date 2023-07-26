package com.spring.security.jwt.service;

import com.spring.security.jwt.models.User;
import com.spring.security.jwt.payload.request.SignupRequest;

import java.util.List;


public interface UserService {
    public Long getUserList();

    public User registerCustomerSignUp(SignupRequest signUpRequest);

    public User registerUser(User userNumber);

    public User updateUserDetail(Long id, User user);

    public User getUserById(Long id);

    public String deleteCustomer(Long customerNumber);

    public User findByUserFirstName(String customerFirstName);

    public User findByUserLastName(String customerLastName);

    public User findByUsername(String username);
}
