package com.spring.security.jwt.service;

import com.spring.security.jwt.models.Customer;

import java.util.List;


public interface CustomerService {

    public List<Customer> getCustomerList();

    public Customer registerCustomer(Customer customerNumber);

    public Customer updateCustomerDetail(Integer customerNumber, Customer customer);

    public Customer getCustomerById(Integer customerNumber);

    public String deleteCustomer(Integer customerNumber);

    public Customer findBycustomerFirstName(String customerFirstName);

    public Customer findBycustomerLastName(String customerLastName);

}
