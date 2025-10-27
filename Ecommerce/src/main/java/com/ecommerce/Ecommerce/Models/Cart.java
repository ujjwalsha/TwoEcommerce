package com.ecommerce.Ecommerce.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    private User user;
//
//    private List<CartItem> cartItems;
//    private Long totalQuantity;
//    private Long totalPrice;
//    private String status;
//    private LocalDateTime createdAt;


}
