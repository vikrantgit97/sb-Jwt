package com.spring.security.jwt.repository;



import com.spring.security.jwt.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findBycustomerFirstName(String customerFirstName);

    Customer findBycustomerLastName(String customerLastName);
}