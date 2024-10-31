package in.vit.security.jwt.security.security;

import in.vit.security.jwt.exception.TokenRefreshException;
import in.vit.security.jwt.models.RefreshToken;
import in.vit.security.jwt.models.User;
import in.vit.security.jwt.repository.RefreshTokenRepository;
import in.vit.security.jwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${app.jwt-refresh-expiration-ms}")
    private long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public RefreshTokenService( RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public void expireToken(RefreshToken token){
        token.setExpired(true);
        token.setRevoked(true);
        token.setExpiryDate(new Date());
        refreshTokenRepository.save(token);
    }

    public RefreshToken createRefreshToken(Long userId, User user) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserId(userId);
        RefreshToken saveNewRefreshToken=new RefreshToken();
        refreshToken.ifPresent(token -> saveNewRefreshToken.setId(token.getId()));
        saveNewRefreshToken.setExpiryDate(new Date(System.currentTimeMillis()+refreshTokenDurationMs));
        saveNewRefreshToken.setToken(UUID.randomUUID().toString());
        saveNewRefreshToken.setUser(user);
        return refreshTokenRepository.save(saveNewRefreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().before(new Date()) && token.isRevoked() && token.isExpired()) {
            //refreshTokenRepository.deleteById(token.getId());
            throw new TokenRefreshException("Refresh token was expired. Please make a new sign-in request");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
