package com.ecommerce.Ecommerce.Controller;

import com.ecommerce.Ecommerce.Models.Role;
import com.ecommerce.Ecommerce.Models.User;
import com.ecommerce.Ecommerce.Repository.RoleRepository;
import com.ecommerce.Ecommerce.Repository.UserRepo;
import com.ecommerce.Ecommerce.SecurityJwt.JwtUtils;
import com.ecommerce.Ecommerce.SecurityRequest.LoginRequest;
import com.ecommerce.Ecommerce.SecurityRequest.SignupRequest;
import com.ecommerce.Ecommerce.SecurityResponse.MessageResponse;
import com.ecommerce.Ecommerce.SecurityResponse.UserInfoResponse;
import com.ecommerce.Ecommerce.SecurityServices.UserDetailImpl;
import com.ecommerce.Ecommerce.Utility.AppRole;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils, UserRepo userRepo,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository)
    {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authentiacteUser(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication;

        try
        {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {

            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);

            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();

        UserDetails userDetails;
        String jwtToken = jwtUtils.GenerateTokenFromUsername(userDetail);
        List<String> roles = userDetail.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        UserInfoResponse response = new UserInfoResponse(userDetail.getId(),  userDetail.getUsername(), jwtToken, roles);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest)
    {

        //two layer of validation in that
        if(userRepo.existsByUserName(signupRequest.getUsername()))
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username is already present"));
        }

        if(userRepo.existsByEmail(signupRequest.getEmail()))
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken"));
        }


        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
        );

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null)
        {
            Role userRole = roleRepository.findByRoleName(AppRole.USER)
                    .orElseThrow(() -> new RuntimeException("Role is not found"));
            roles.add(userRole);
        }
        else {
            strRoles.forEach( role ->{
                switch (role)
                {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName(AppRole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "seller":
                        Role sellerRole = roleRepository.findByRoleName(AppRole.SELLER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(sellerRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName(AppRole.USER)
                                .orElseThrow(() -> new RuntimeException("Role is not found"));
                        roles.add(userRole);

                }
            });
        }


        user.setRoles(roles);
        userRepo.save(user);

        return ResponseEntity.ok(new MessageResponse("User register successfully! "));
    }
}
