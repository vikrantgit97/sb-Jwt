package com.spring.security.jwt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 15, message = "Customer First Name Must be between 3 to 15")
    @Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String userFirstName;

    @Size(min = 3, max = 15, message = "Customer Last Name Must be between 3 to 15")
    @Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String userLastName;

    @Pattern(regexp = "^[0-9]{10}", message = "Invalid Mobile Number")
    private String phone;

    @NotBlank(message = "Address line 1 cannot be blank")
    private String addressLine1;

    @Size(max = 50, message = "Address line 2 cannot be more than 50 characters")
    private String addressLine2;

    @NotBlank(message = "City cannot be blank")
    @Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String state;

    private Integer postalCode;

    @NotBlank(message = "Country cannot be blank")
    @Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String country;

    @Size(min = 3, max = 15, message = "Customer First Name Must be between 3 to 15")
    @Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @NonNull
    private String email;

    @Size(min = 3, max = 120, message = "password Must be between 6 to 120")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role.RoleName> roles;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String userFirstName, String userLastName, String phone, String addressLine1, String addressLine2, String city, String state, Integer postalCode, String country, String username, @NonNull String email, String password, Set<Role.RoleName> roles) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public User(String userFirstName, String userLastName, String phone, String addressLine1, String addressLine2, String city, String state, Integer postalCode, String country, String username, String email, String password) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}