package in.vit.security.jwt.repository;

import in.vit.security.jwt.models.RefreshToken;
import in.vit.security.jwt.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    /*@Query("SELECT rt FROM RefreshToken rt JOIN FETCH rt.user WHERE rt.user.id = :userId")
    Optional<RefreshToken> fetchRefreshTokenFromUserId(@Param("userId") Long userId);*/

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.user.id = :userId")
    Optional<RefreshToken> findByUserId(@Param("userId") Long userId);
    @Modifying
    int deleteByUser(User user);

    @Transactional
    int deleteById(long id);
}
