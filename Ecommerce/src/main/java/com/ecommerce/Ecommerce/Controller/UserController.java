package com.ecommerce.Ecommerce.Controller;

import com.ecommerce.Ecommerce.Models.User;
import com.ecommerce.Ecommerce.Models.UserRequest;
import com.ecommerce.Ecommerce.Service.UserService;
import com.ecommerce.Ecommerce.Utility.PasswordGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final PasswordGenerator passwordGenerator;

    public UserController(UserService userService, PasswordGenerator passwordGenerator)
    {
        this.userService = userService;
        this.passwordGenerator = passwordGenerator;
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping("/users/generate")
    public ResponseEntity<String> suggestRandomPassword(){
        return passwordGenerator.generateRandomPassword(12);
    }

    @GetMapping("/users/me")
    public ResponseEntity<?> getLoggedInUser(HttpServletRequest request)
    {
        return userService.getLoggedInUser(request);
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> LoginUser(@RequestBody UserRequest userRequest)
    {
        return userService.loginUser(userRequest);
    }

    @PutMapping("users/update")
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @PostMapping("/users/logout")
    public ResponseEntity<?> logout(){
        ResponseCookie deleteCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build();


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, deleteCookie.toString()).body(Map.of("message", "Logged out successfully!"));
    }




}
