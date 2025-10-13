package com.ecommerce.Ecommerce.Service;


import com.ecommerce.Ecommerce.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService( OrderRepo orderRepo){
        this.orderRepo =orderRepo;
    }


}
