package com.spring.security.jwt.repository;

import com.spring.security.jwt.models.RefreshToken;
import com.spring.security.jwt.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    //Optional<RefreshToken> findByUser(Long userId);

    @Modifying
    int deleteByUser(User user);

    @Transactional
    int deleteById(long id);
}
