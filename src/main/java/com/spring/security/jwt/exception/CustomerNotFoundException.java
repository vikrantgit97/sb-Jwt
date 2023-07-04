package com.spring.security.jwt.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}