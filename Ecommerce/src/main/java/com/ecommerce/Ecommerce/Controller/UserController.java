package com.ecommerce.Ecommerce.Controller;

import com.ecommerce.Ecommerce.Models.User;
import com.ecommerce.Ecommerce.Models.UserRequest;
import com.ecommerce.Ecommerce.Service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(UserRequest userRequest)
    {
        return userService.loginUser(userRequest);
    }

}
