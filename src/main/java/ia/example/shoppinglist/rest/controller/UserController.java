package ia.example.shoppinglist.rest.controller;

import ia.example.shoppinglist.domain.User;
import ia.example.shoppinglist.repositories.UserRepository;

@org.springframework.web.bind.annotation.RestController
public class UserController extends RestController<User> {

    public UserController(UserRepository userRepository) {

        super(userRepository);
    }
}
