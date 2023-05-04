package com.spring.security.jwt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.security.jwt.customvalidation.PostalCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 15, message = "Customer First Name Must be between 3 to 15")
    @Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String customerFirstName;

    @Size(min = 3, max = 15, message = "Customer Last Name Must be between 3 to 15")
    @Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String customerLastName;

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

    @PostalCode
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
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Customer(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Customer(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Customer(String customerFirstName, String customerLastName, String phone, String addressLine1, String addressLine2, String city, String state, Integer postalCode, String country, String username, @NonNull String email, String password, Set<Role> roles) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
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


    public Customer(String customerFirstName, String customerLastName, String phone, String addressLine1, String addressLine2, String city, String state, Integer postalCode, String country, String username, String email, String password) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
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