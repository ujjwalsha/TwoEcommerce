package com.ecommerce.Ecommerce.Controller;

import com.ecommerce.Ecommerce.Service.CartItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartitem")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService)
    {
        this.cartItemService = cartItemService;
    }





}
