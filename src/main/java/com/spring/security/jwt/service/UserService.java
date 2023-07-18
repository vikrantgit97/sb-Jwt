package com.spring.security.jwt.service;

import com.spring.security.jwt.models.User;
import com.spring.security.jwt.payload.request.SignupRequest;

import java.util.List;


public interface UserService {
    public Long getUserList();

    public User registerCustomerSignUp(SignupRequest signUpRequest);

    public User registerUser(User userNumber);

    public User updateUserDetail(Long customerNumber, User user);

    public User getUserById(Long customerNumber);

    public String deleteCustomer(Long customerNumber);

    public User findByuserFirstName(String customerFirstName);

    public User findByuserLastName(String customerLastName);

    public User findByUsername(String username);
}
