package com.spring.security.jwt.service;

import com.spring.security.jwt.models.OrderDetails;

import java.util.List;

public interface OrderDetailsService {

    public void save(List<OrderDetails> orderDetailsList);

}

