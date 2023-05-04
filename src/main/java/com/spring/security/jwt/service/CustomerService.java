package com.spring.security.jwt.service;

import com.spring.security.jwt.models.Customer;
import com.spring.security.jwt.payload.request.SignupRequest;

import java.util.List;


public interface CustomerService {
    public List<Customer> getCustomerList();

    public Customer registerCustomerSignUp(SignupRequest signUpRequest);

    public Customer registerCustomer(Customer customerNumber);

    public Customer updateCustomerDetail(Long customerNumber, Customer customer);

    public Customer getCustomerById(Long customerNumber);

    public String deleteCustomer(Long customerNumber);

    public Customer findBycustomerFirstName(String customerFirstName);

    public Customer findBycustomerLastName(String customerLastName);

}
