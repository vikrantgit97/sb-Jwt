package com.spring.security.jwt.serviceimpl;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.spring.security.jwt.exception.CustomerNotFoundException;
import com.spring.security.jwt.models.Customer;
import com.spring.security.jwt.models.ERole;
import com.spring.security.jwt.models.Role;
import com.spring.security.jwt.payload.request.SignupRequest;
import com.spring.security.jwt.repository.CustomerRepository;
import com.spring.security.jwt.repository.RoleRepository;
import com.spring.security.jwt.security.jwt.JwtUtils;
import com.spring.security.jwt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository iCustomerRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<Customer> getCustomerList() {
        return iCustomerRepo.findAll();
    }

    @Override
    public Customer updateCustomerDetail(Long customerNumber, Customer c) {
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
    public Customer getCustomerById(Long customerNumber) {
        return iCustomerRepo.findById(customerNumber).orElseThrow(() ->
                new CustomerNotFoundException("Customer cannot be created: " + customerNumber));
    }

    @Override
    public Customer registerCustomer(Customer customerNumber) {
        return iCustomerRepo.save(customerNumber);
    }

    @Override
    public Customer registerCustomerSignUp(SignupRequest signUpRequest) {
        try {
            if (iCustomerRepo.existsByUsername(signUpRequest.getUsername())) {
                throw new RoleNotFoundException("Error: Username is already taken!");
            }
            if (iCustomerRepo.existsByEmail(signUpRequest.getEmail())) {
                throw new RoleNotFoundException("Error: Email is already in use!");
            }
            //Create new user's account
            //Customer user = new Customer(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
            Customer user = new Customer(signUpRequest.getCustomerFirstName(), signUpRequest.getCustomerLastName(), signUpRequest.getPhone(),
                    signUpRequest.getAddressLine1(), signUpRequest.getAddressLine2(),
                    signUpRequest.getCity(),
                    signUpRequest.getState(),
                    signUpRequest.getPostalCode(),
                    signUpRequest.getCountry(),
                    signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword())
            );

            Set<String> strRoles = signUpRequest.getRoles();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);

                            break;
                        case "moderator":
                            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);

                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            user.setRoles(roles);
            Customer customer = iCustomerRepo.save(user);
            return customer;
        } catch (RoleNotFoundException runtimeException) {
            throw new CustomerNotFoundException("customer not registered");
        }
    }

    @Override
    public String deleteCustomer(Long customerNumber) {
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
