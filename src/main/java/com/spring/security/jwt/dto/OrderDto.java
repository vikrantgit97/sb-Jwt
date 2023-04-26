package com.spring.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto  {

    private Integer orderNumber;

    private LocalDate orderDate;

    private LocalDate shippedDate;

    private Status status;

    private String comments;

    private Integer customerNumber;

    private List<OrderDetailsDto> orderDetails;

}