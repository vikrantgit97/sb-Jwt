package com.spring.security.jwt.repository;



import com.spring.security.jwt.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findBycustomerFirstName(String customerFirstName);

    Customer findBycustomerLastName(String customerLastName);

    Optional<Customer> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}