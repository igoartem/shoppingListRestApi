package ia.example.shoppinglist.rest.controller;

import ia.example.shoppinglist.rest.dto.UserDto;
import ia.example.shoppinglist.rest.service.BasicService;

//@org.springframework.web.bind.annotation.RestController("user")
public class UserController extends RestController<UserDto> {

    public UserController(BasicService basicService) {
        super(basicService);
    }
}
