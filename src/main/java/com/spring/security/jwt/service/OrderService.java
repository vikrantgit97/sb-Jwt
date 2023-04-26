package com.spring.security.jwt.service;

import com.spring.security.jwt.models.Order;

import javax.validation.Valid;
import java.util.List;

public interface OrderService {

    public List<Order> getAllOrder();

    public Order createOrder(Order orderDto);

    public Order findOrderById(@Valid Integer orderNumber);

    public String deleteOrder(@Valid Integer orderNumber) ;

    public Order updateShippingDate(@Valid Integer orderNumber, @Valid Order order);

    Order updateOrderStatus(Integer orderNumber, Order order);

    public Order updateComment(Integer orderNumber, Order order);

    //public Order createOrder2(Order order);


}

