package com.spring.security.jwt.service;

import com.spring.security.jwt.models.User;
import com.spring.security.jwt.payload.request.SignupRequest;

import java.util.List;


public interface UserService {
    public List<User> getCustomerList();

    public User registerCustomerSignUp(SignupRequest signUpRequest);

    public User registerCustomer(User userNumber);

    public User updateCustomerDetail(Long customerNumber, User user);

    public User getCustomerById(Long customerNumber);

    public String deleteCustomer(Long customerNumber);

    public User findBycustomerFirstName(String customerFirstName);

    public User findBycustomerLastName(String customerLastName);

}
