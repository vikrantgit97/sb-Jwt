package com.spring.security.jwt.runner;

import com.github.javafaker.Faker;
import com.spring.security.jwt.models.Customer;
import com.spring.security.jwt.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.Locale;

//@Component
@Order(2)
public class CustomerRunner implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepo;

    public void run(String... args) throws Exception {
        if (customerRepo.count() == 0) {
            for (int i = 1; i < 100; i++) {
                Faker faker = new Faker(new Locale("en-IND"));
                Customer customer = new Customer();
                customer.setCustomerFirstName(faker.name().firstName());
                customer.setCustomerLastName(faker.name().lastName());
                customer.setPhone(String.valueOf(faker.number().numberBetween(1000000000L, 9999999999L)));
                customer.setAddressLine1(faker.address().streetAddress());
                customer.setAddressLine2(faker.address().secondaryAddress());
                customer.setCity(faker.address().city());
                customer.setState(faker.address().state());
                customer.setPostalCode(faker.number().numberBetween(100000, 999999));
                customer.setCountry(faker.address().country());
                customerRepo.save(customer);
            }
        }
    }
}
