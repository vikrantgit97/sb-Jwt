package com.spring.security.jwt.dto;
import lombok.Getter;

@Getter
public enum Status {
    CREATED, ORDERED, CANCELED, PENDING, SHIPPED, DELIVERED;
}