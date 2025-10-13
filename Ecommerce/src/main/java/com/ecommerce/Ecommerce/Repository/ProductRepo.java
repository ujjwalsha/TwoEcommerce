package com.ecommerce.Ecommerce.Repository;

import com.ecommerce.Ecommerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Long categoryId);

    Optional<Product> findByName(String name);
}
