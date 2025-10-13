package com.ecommerce.Ecommerce.Controller;

import com.ecommerce.Ecommerce.Models.User;
import com.ecommerce.Ecommerce.Models.UserRequest;
import com.ecommerce.Ecommerce.Service.UserService;
import com.ecommerce.Ecommerce.Utility.PasswordGenerator;
import jakarta.persistence.GeneratedValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordGenerator passwordGenerator;

    public UserController(UserService userService, PasswordGenerator passwordGenerator)
    {
        this.userService = userService;
        this.passwordGenerator = passwordGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping("/generate")
    public ResponseEntity<String> suggestRandomPassword(){
        return passwordGenerator.generateRandomPassword(12);
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody UserRequest userRequest)
    {
        return userService.loginUser(userRequest);
    }

}
