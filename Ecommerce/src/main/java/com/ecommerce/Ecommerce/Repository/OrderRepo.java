package com.ecommerce.Ecommerce.Repository;


import com.ecommerce.Ecommerce.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
