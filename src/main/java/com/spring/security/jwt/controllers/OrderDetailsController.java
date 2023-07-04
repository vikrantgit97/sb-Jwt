package com.spring.security.jwt.controllers;


import com.spring.security.jwt.dto.SuccessDetails;
import com.spring.security.jwt.models.OrderDetails;
import com.spring.security.jwt.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsRepository orderDetailsRepo;

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderDetails order) {
        OrderDetails orderCreated = orderDetailsRepo.save(order);
        return (SuccessDetails.success("order created successfully", orderCreated, HttpStatus.CREATED));
    }

    @PostMapping("/list")
    public ResponseEntity<?> addOrder1(@RequestBody List<OrderDetails> order) {
        List<OrderDetails> orderCreated = orderDetailsRepo.saveAll(order);
        return (SuccessDetails.success("orders created successfully", orderCreated, HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<?> getOrder() {
        return (SuccessDetails.success("order fetch successfully", orderDetailsRepo.findAll()));
    }
}