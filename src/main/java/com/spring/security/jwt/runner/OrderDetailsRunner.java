package com.spring.security.jwt.runner;

import com.github.javafaker.Faker;
import com.spring.security.jwt.models.OrderDetails;
import com.spring.security.jwt.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@org.springframework.core.annotation.Order(20)
public class OrderDetailsRunner implements CommandLineRunner {

    @Autowired
    private OrderDetailsRepository orderDetailsRepo;

    @Override
    public void run(String... args) throws Exception {
        if (orderDetailsRepo.count() == 0) {
            for (int i = 1; i < 5; i++) {
                Faker faker = new Faker(new Locale("en-IND"));
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderNumber(i);
                orderDetails.setQuantityOrdered(faker.number().numberBetween(1, 2));
                orderDetails.setProductCode(2);
                orderDetails.setPriceEach(faker.number().randomDouble(2, 100, 1000));
                orderDetailsRepo.save(orderDetails);
            }
        }
    }
}