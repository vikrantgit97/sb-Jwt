package in.vit.security.jwt.repository;


import in.vit.security.jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserFirstName(String userFirstName);

    User findByUserLastName(String userLastName);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}