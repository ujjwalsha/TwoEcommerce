package com.ecommerce.Ecommerce.Repository;

import com.ecommerce.Ecommerce.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

}
