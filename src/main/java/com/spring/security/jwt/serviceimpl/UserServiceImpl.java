package com.spring.security.jwt.serviceimpl;

import com.spring.security.jwt.models.Role;
import com.spring.security.jwt.models.User;
import com.spring.security.jwt.payload.request.SignupRequest;
import com.spring.security.jwt.repository.UserRepository;
import com.spring.security.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public Long getUserList() {
        return repository.findAll().stream().count();
    }

    @Override
    public User registerCustomerSignUp(SignupRequest signUpRequest) {
        User user = new User();
        user.setUserFirstName(signUpRequest.getUserFirstName());
        user.setUserLastName(signUpRequest.getUserLastName());
        user.setAddressLine1(signUpRequest.getAddressLine1());
        user.setAddressLine2(signUpRequest.getAddressLine2());
        user.setCity(signUpRequest.getCity());
        user.setState(signUpRequest.getState());
        user.setCountry(signUpRequest.getCountry());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setPhone(signUpRequest.getPhone());
        user.setPostalCode(signUpRequest.getPostalCode());
        user.setRoles(Collections.singleton(Role.RoleName.ROLE_USER));
        User save = repository.save(user);
        return save;
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User Not found"));
    }

    @Override
    public User registerUser(User userNumber) {
        return null;
    }

    @Override
    public User updateUserDetail(Long customerNumber, User user) {
        return null;
    }

    @Override
    public User getUserById(Long customerNumber) {
        return null;
    }

    @Override
    public String deleteCustomer(Long customerNumber) {
        return null;
    }

    @Override
    public User findByuserFirstName(String customerFirstName) {
        return null;
    }

    @Override
    public User findByuserLastName(String customerLastName) {
        return null;
    }
}
