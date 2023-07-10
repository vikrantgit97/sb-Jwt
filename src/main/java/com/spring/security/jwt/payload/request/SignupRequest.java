package com.spring.security.jwt.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.validation.constraints.*;
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SignupRequest {

    private Long id;

    private String customerFirstName;

    private String customerLastName;

    @Pattern(regexp = "^[0-9]{10}", message = "Invalid Mobile Number")
    private String phone;

    @NotBlank(message = "Address line 1 cannot be blank")
    private String addressLine1;

    @Size(max = 50, message = "Address line 2 cannot be more than 50 characters")
    private String addressLine2;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    private Integer postalCode;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @Size(min = 3, max = 15, message = "UserName Must be between 3 to 15")
    //@Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Size(min = 3, max = 120, message = "password Must be between 6 to 120")
    private String password;

    private Set<String> roles;

}
