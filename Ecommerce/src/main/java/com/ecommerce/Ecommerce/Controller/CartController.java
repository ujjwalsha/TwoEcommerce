package com.ecommerce.Ecommerce.Controller;


import com.ecommerce.Ecommerce.Service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }
}
