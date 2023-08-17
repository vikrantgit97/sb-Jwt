package com.spring.security.jwt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity(name = "refresh_token")
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;*/

    /*@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    public User userId;*/

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    public boolean revoked;

    public boolean expired;
}


