package com.ecommerce.Ecommerce.Repository;

import com.ecommerce.Ecommerce.Models.Category;
import com.ecommerce.Ecommerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//it is indicate that a class is a data access Object(DAO), this class handle persistance logic
// persistence logic means -- this class responsible for managing how data is stored, retrieved, updated, and deleted.
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
