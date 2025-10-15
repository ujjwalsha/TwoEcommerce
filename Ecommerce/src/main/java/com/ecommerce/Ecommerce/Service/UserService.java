package com.ecommerce.Ecommerce.Service;


import com.ecommerce.Ecommerce.Models.User;
import com.ecommerce.Ecommerce.Models.UserRequest;
import com.ecommerce.Ecommerce.Repository.UserRepo;
import com.ecommerce.Ecommerce.Utility.JwtService;
import com.ecommerce.Ecommerce.Utility.Role;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final JwtService jwtService;

    public UserService(UserRepo userRepo, JwtService jwtService)
    {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }


    public ResponseEntity<?> registerUser(User user) {

        if(userRepo.existsByEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already registered");
        }

        if(user.getUsername() == null || user.getUsername().trim().isEmpty())
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username is required");
        }

        if(user.getPhone() == null || user.getPhone().trim().isEmpty() || user.getPhone().length() <10)
        {
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
        temp.setProfileUrl(user.getProfileUrl());
        temp.setPhone(user.getPhone());
        temp.setRole(user.getRole() != null ? user.getRole() : Role.USER);  //default user

        userRepo.save(temp);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> loginUser(UserRequest userRequest) {

        Optional<User> user  = userRepo.findByPassword(userRequest.getPassword());

        if(user.isEmpty() && !userRepo.existsByUsername(userRequest.getUsername()))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found!");
        }


        String token = jwtService.generateToken(user.get());

        System.out.println("token is : "+ token);

        return ResponseEntity.status(HttpStatus.OK).body(token);
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
        temp.setProfileUrl(user.getProfileUrl());

        userRepo.save(temp);

        return ResponseEntity.status(HttpStatus.OK).body("user updated successfully !");
    }


}
