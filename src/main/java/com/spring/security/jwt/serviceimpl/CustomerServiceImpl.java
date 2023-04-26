package com.spring.security.jwt.serviceimpl;
import java.util.List;
import java.util.Optional;

import com.spring.security.jwt.exception.CustomerNotFoundException;
import com.spring.security.jwt.models.Customer;
import com.spring.security.jwt.repository.CustomerRepository;
import com.spring.security.jwt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository iCustomerRepo;

    @Override
    public List<Customer> getCustomerList() {
        return iCustomerRepo.findAll();
    }

    @Override
    public Customer updateCustomerDetail(Integer customerNumber, Customer c) {
        Optional<Customer> customer1 = iCustomerRepo.findById(customerNumber);
        if (customer1.isPresent()) {
            Customer customer = customer1.get();
            customer.setCustomerFirstName(c.getCustomerFirstName());
            customer.setCustomerLastName(c.getCustomerLastName());
            customer.setAddressLine1(c.getAddressLine1());
            customer.setAddressLine2(c.getAddressLine2());
            customer.setCity(c.getCity());
            customer.setCountry(c.getCountry());
            customer.setPostalCode(c.getPostalCode());
            customer.setState(c.getState());
            customer.setPhone(c.getPhone());
            return iCustomerRepo.save(customer);
        } else {
            throw new CustomerNotFoundException("Id does Not Exist");
        }
    }

    @Override
    public Customer getCustomerById(Integer customerNumber) {


        return iCustomerRepo.findById(customerNumber).orElseThrow(() ->
                new CustomerNotFoundException("Customer cannot be created: " + customerNumber));

    }

    @Override
    public Customer registerCustomer(Customer customerNumber) {
        return iCustomerRepo.save(customerNumber);
    }

    @Override
    public String deleteCustomer(Integer customerNumber) {
        try {
            if (customerNumber != null) {
                iCustomerRepo.deleteById(customerNumber);
            }
            return "One Customer deleted " + customerNumber;
        } catch (RuntimeException e) {
            throw new CustomerNotFoundException("Id does Not Exist");
        }

    }

    @Override
    public Customer findBycustomerFirstName(String customerFirstName) {

        return iCustomerRepo.findBycustomerFirstName(customerFirstName);
    }

    @Override
    public Customer findBycustomerLastName(String customerLasstName) {

        return iCustomerRepo.findBycustomerLastName(customerLasstName);
    }

}
