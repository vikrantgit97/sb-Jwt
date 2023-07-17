package com.spring.security.jwt.repository;

import java.util.List;
import java.util.Optional;

import com.spring.security.jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.security.jwt.models.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  @Query(value = """
      select t from RefreshToken t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<RefreshToken> findAllValidTokenByUser(Integer id);

  Optional<RefreshToken> findByToken(String token);

  @Modifying
  int deleteByUser(User user);
}
