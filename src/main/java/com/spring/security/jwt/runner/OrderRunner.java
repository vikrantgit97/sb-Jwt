package com.spring.security.jwt.runner;

import com.github.javafaker.Faker;
import com.spring.security.jwt.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@org.springframework.core.annotation.Order(10)
@Component
public class OrderRunner implements CommandLineRunner {

    @Autowired
    private OrderRepository orderRepo;

    @Override
    public void run(String... args) throws Exception {
        if (orderRepo.count() == 0) {
            for (int i = 1; i < 5; i++) {
                Faker faker = new Faker(new Locale("en-IND"));
                Order order = new Order();
                order.setOrderDate(LocalDate.now());
                order.setComments(faker.lorem().sentence());
                order.setStatus(Status.ORDERED);
                order.setCustomerNumber(faker.number().randomDigit());
                order.setShippedDate(LocalDate.now().plusDays(2));
                order.setOrderDetails(List.of());
                orderRepo.save(order);
            }
        }
    }
}

