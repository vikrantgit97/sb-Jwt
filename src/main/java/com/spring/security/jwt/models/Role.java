package com.spring.security.jwt.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleName roleName;

    public enum RoleName {
        ROLE_USER,
        ROLE_MODERATOR,
        ROLE_ADMIN
    }

    public String getAuthority() {
        return roleName.name();
    }
}

