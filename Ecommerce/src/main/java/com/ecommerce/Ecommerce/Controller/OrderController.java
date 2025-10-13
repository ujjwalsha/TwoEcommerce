package com.ecommerce.Ecommerce.Controller;


import com.ecommerce.Ecommerce.Models.Order;
import com.ecommerce.Ecommerce.Service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }
}
