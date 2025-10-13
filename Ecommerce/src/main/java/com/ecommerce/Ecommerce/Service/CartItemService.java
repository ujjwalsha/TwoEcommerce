package com.ecommerce.Ecommerce.Service;

import com.ecommerce.Ecommerce.Repository.CartItemRepo;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepo cartItemRepo;

    public CartItemService(CartItemRepo cartItemRepo)
    {
        this.cartItemRepo = cartItemRepo;
    }
}
