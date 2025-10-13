package com.ecommerce.Ecommerce.Controller;

import com.ecommerce.Ecommerce.Service.CartItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartitem")
public class CartItem {
    private final CartItemService cartItemService;

    public CartItem(CartItemService cartItemService)
    {
        this.cartItemService = cartItemService;
    }
}
