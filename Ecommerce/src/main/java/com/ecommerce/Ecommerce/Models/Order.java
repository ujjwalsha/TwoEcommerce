package com.ecommerce.Ecommerce.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String OrderStatus;
    private double Amount;

    @JsonIgnore
    private LocalDateTime createAt = LocalDateTime.now();

    @JsonIgnore
    private LocalDateTime updateAt = LocalDateTime.now();

}
