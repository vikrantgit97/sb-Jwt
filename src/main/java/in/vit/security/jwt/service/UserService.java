package in.vit.security.jwt.service;

import in.vit.security.jwt.models.User;
import in.vit.security.jwt.payload.request.SignupRequest;


public interface UserService {
    Long getUserList();

    User registerCustomerSignUp(SignupRequest signUpRequest);

    User registerUser(User userNumber);

    User updateUserDetail(Long id, User user);

    User getUserById(Long id);

    String deleteCustomer(Long customerNumber);

    User findByUserFirstName(String customerFirstName);

    User findByUserLastName(String customerLastName);

    User findByUsername(String username);
}
