package com.ecommerce.Ecommerce.Service;


import com.ecommerce.Ecommerce.Models.User;
import com.ecommerce.Ecommerce.Models.UserRequest;
import com.ecommerce.Ecommerce.Repository.UserRepo;
import com.ecommerce.Ecommerce.Utility.JwtService;
import com.ecommerce.Ecommerce.Utility.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepo userRepo, JwtService jwtService)
    {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }


    public ResponseEntity<?> registerUser(User user) {

        System.out.println(user);

        if(userRepo.existsByEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Users already exists!"));
        }

        if(user.getUsername() == null || user.getUsername().trim().isEmpty())
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username is required");
        }

        if(user.getPhone() == null || user.getPhone().trim().isEmpty() || user.getPhone().length() < 10)
        {
            System.out.println("length is : "+ user.getPhone());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("phone number must be length 10");
        }

        if(user.getEmail() == null || !user.getEmail().contains("@"))
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid email");
        }

        //(?=.*[A-Z])  -- somewhere ahead in the string, there must be at least one uppercase character


        String PASSWORD_PATTERN =
                "^(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&+=!-])" + ".{8,}$";

        if(user.getPassword() == null || !user.getPassword().matches(PASSWORD_PATTERN))
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Password must be at least 8 characters long and include uppercase, lowercase, number and special character");
        }


        User temp = new User();
        temp.setUsername(user.getUsername());
        temp.setEmail(user.getEmail());

        temp.setPassword(user.getPassword());
        temp.setIsActive(user.getIsActive());
        temp.setPhone(user.getPhone());
        temp.setRole(user.getRole() != null ? user.getRole() : Role.USER);  //default user

        userRepo.save(temp);

        return ResponseEntity.status(HttpStatus.CREATED).body("Register successfully!");
    }

    public ResponseEntity<?> loginUser(UserRequest userRequest) {

        Optional<User> user  = userRepo.findByPassword(userRequest.getPassword().trim());

        if(user.isEmpty() && !userRepo.existsByUsername(userRequest.getUsername().trim()))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found!");
        }

        new User();
        User existUser;

        System.out.println(userRequest);

        if(user.isPresent())
        {
            existUser = user.get();
        }
        else {
            throw new RuntimeException("User not found");
        }

        String token = jwtService.generateToken(existUser);


        System.out.println("token is : "+ token);

        //send jwt to cookies
        ResponseCookie cookie = ResponseCookie.from("jwt",token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(24*60*60)
                .build();

        String username = jwtService.extractUsername(token);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(Map.of("message" , "Login successful"));
    }

    public ResponseEntity<?> updateUser(User user) {

        if(!userRepo.existsByUsername(user.getUsername()))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        User temp = new User();
        temp.setUsername(user.getUsername());
        temp.setEmail(user.getEmail());
        temp.setPhone(user.getPhone());

        userRepo.save(temp);

        return ResponseEntity.status(HttpStatus.OK).body("user updated successfully !");
    }


    public ResponseEntity<?> getLoggedInUser(HttpServletRequest request) {

        System.out.println(request.getAttribute("username"));
        System.out.println(request.getRequestURL());

        String username = (String)request.getAttribute("username");

        if(username == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message" , "Unauthorized"));
        }

        User user = userRepo.findByUsername(username);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
