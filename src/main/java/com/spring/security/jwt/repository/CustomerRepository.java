package com.spring.security.jwt.repository;



import com.spring.security.jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {
    User findBycustomerFirstName(String customerFirstName);

    User findBycustomerLastName(String customerLastName);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}