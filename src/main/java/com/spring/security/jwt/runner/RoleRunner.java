package com.spring.security.jwt.runner;

import com.spring.security.jwt.models.User;
import com.spring.security.jwt.models.ERole;
import com.spring.security.jwt.models.Role;
import com.spring.security.jwt.repository.CustomerRepository;
import com.spring.security.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RoleRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void run(String... args) throws Exception {

        Set<Role> roles = new HashSet<>();
        List<User> users = new ArrayList<>();
        if (repository.count() == 0) {
            if (!repository.findByName(ERole.ROLE_USER).isPresent()) {
                Role user = new Role();
                user.setName(ERole.ROLE_USER);
                repository.save(user);
                roles.add(user);
                users.add(new User("Jane", "Doe", "0987654321", "456 Elm St", "north", "Los Angeles", "CA", 900001, "USA", "janedoe", "janedoe@email.com", passwordEncoder.encode("password"), Collections.singleton(user)));
                users.add(new User("Bob", "Smith", "1112223333", "789 Maple Ave", "north", "Chicago", "IL", 606001, "USA", "bobsmith", "bobsmith@email.com", passwordEncoder.encode("password"), Collections.singleton(user)));
                users.add(new User("Alice", "Johnson", "5556667777", "321 Oak St", "north", "Houston", "TX", 770001, "USA", "alicejohnson", "alicejohnson@email.com", passwordEncoder.encode("password"), Collections.singleton(user)));
                users.add(new User("Mark", "Brown", "7778889999", "987 Pine Rd", "north", "San Francisco", "CA", 941001, "USA", "markbrown", "markbrown@email.com", passwordEncoder.encode("password"), Collections.singleton(user)));
                users.add(new User("Sarah", "Davis", "4445556666", "654 Cedar Ln", "north", "Boston", "MA", 921001, "USA", "sarahdavis", "sarahdavis@email.com", passwordEncoder.encode("password"), Collections.singleton(user)));
            }
            if (!repository.findByName(ERole.ROLE_ADMIN).isPresent()) {
                Role admin = new Role();
                admin.setName(ERole.ROLE_ADMIN);
                repository.save(admin);
                roles.add(admin);
                users.add(new User("Jennifer", "Lee", "9998887777", "246 Elmwood Ave", "north", "Miami", "FL", 331001, "USA", "jenniferlee", "jenniferlee@email.com", passwordEncoder.encode("password"), Collections.singleton(admin)));
                users.add(new User("John", "Doe", "1234567890", "123 Main St", "north", "New York", "NY", 100001, "USA", "john", "johndoe@email.com", passwordEncoder.encode("password"), Collections.singleton(admin)));
                users.add(new User("Brock", "Lesnar", "9234567890", "lesnar yard nyc 56", "north", "New York", "NY", 165001, "USA", "brock", "brock@email.com", passwordEncoder.encode("password"), Collections.singleton(admin)));
                users.add(new User("Bill", "Goldbregr", "7234567890", "north submarine 65", "north", "New York", "NY", 198001, "USA", "bill", "bill@email.com", passwordEncoder.encode("password"), Collections.singleton(admin)));
            }
            /*if (!repository.findByName(ERole.ROLE_ADMIN).isPresent()) {
                Role moderator = new Role();
                moderator.setName(ERole.ROLE_ADMIN);
                repository.save(moderator);
                roles.add(moderator);
                customers.add(new Customer("Tom", "Wilson", "2223334444", "753 Birch Dr", "north", "Seattle", "WA", 981001, "USA", "tomwilson", "tomwilson@email.com", passwordEncoder.encode("password"), Collections.singleton(moderator)));
                customers.add(new Customer("Naina", "lamba", "6223334444", "753 Birch Dr", "north", "Seattle", "WA", 881001, "USA", "naina", "naina@email.com", passwordEncoder.encode("password"), Collections.singleton(moderator)));
            }*/
            if (!repository.findByName(ERole.ROLE_MODERATOR).isPresent()) {
                Role delivery_agent = new Role();
                delivery_agent.setName(ERole.ROLE_MODERATOR);
                repository.save(delivery_agent);
                roles.add(delivery_agent);
                users.add(new User("Roman", "Raings", "6593334444", "65 Dadat prince", "south", "Peninsula", "LA", 381001, "USA", "roman", "roman@email.com", passwordEncoder.encode("password"), Collections.singleton(delivery_agent)));
                users.add(new User("Kofi", "kingston", "7823334444", "658 NYC street", "south", "Candi", "LA", 681001, "USA", "kofi", "kofi@email.com", passwordEncoder.encode("password"), Collections.singleton(delivery_agent)));
            }
            users.add(new User("David", "Nguyen", "6667778888", "951 Cherry St", "north", "Portland", "OR", 972001, "USA", "davidnguyen", "davidnguyen@email.com", passwordEncoder.encode("password"), roles));
            users.add(new User("Emily", "Garcia", "3334445555", "369 Willow Ln", "north", "Atlanta", "GA", 303001, "USA", "emilygarcia", "emilygarcia@email.com", passwordEncoder.encode("password"), roles));
            customerRepo.saveAll(users);
        }
    }
}