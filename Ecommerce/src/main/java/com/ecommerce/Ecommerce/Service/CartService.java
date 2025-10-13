package com.ecommerce.Ecommerce.Service;

import com.ecommerce.Ecommerce.Repository.CartRepo;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepo cartRepo;

    public CartService(CartRepo cartRepo)
    {
        this.cartRepo = cartRepo;
    }


}
