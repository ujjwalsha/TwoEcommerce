package com.ecommerce.Ecommerce.Service;


import com.ecommerce.Ecommerce.Models.User;
import com.ecommerce.Ecommerce.Models.UserRequest;
import com.ecommerce.Ecommerce.Repository.UserRepo;
import com.ecommerce.Ecommerce.Utility.JwtService;
import com.ecommerce.Ecommerce.Utility.Role;
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


        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
