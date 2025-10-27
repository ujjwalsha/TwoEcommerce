package com.ecommerce.Ecommerce.Models;


import com.ecommerce.Ecommerce.Utility.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(nullable = false)
    private String email;
    private String password;
    private String phone;
    private Boolean isActive = true;
    private String profileUrl;

    @JsonIgnore
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){
        this.createdAt = LocalDateTime.now();
    }
}
