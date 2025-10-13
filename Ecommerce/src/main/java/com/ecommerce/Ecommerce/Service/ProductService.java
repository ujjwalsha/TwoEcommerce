package com.ecommerce.Ecommerce.Service;

import com.ecommerce.Ecommerce.Controller.CategoryController;
import com.ecommerce.Ecommerce.Controller.ProductController;
import com.ecommerce.Ecommerce.Models.Category;
import com.ecommerce.Ecommerce.Models.Product;
import com.ecommerce.Ecommerce.Repository.CategoryRepo;
import com.ecommerce.Ecommerce.Repository.ProductRepo;
import org.hibernate.persister.collection.mutation.UpdateRowsCoordinatorTablePerSubclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo)
    {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }


    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    /**
     * when you call findById(id) -- it returns an Optional<Product> object.
     * Optional is wrapper that may or may not contains a value.
     * get() is used to retrieve the value from the optional.
     */

    public Product getProductById(int id) {
        return productRepo.findById(id).get();
    }

    public List<Product> getByCategoryId(Long categoryId) {

        return productRepo.findByCategoryId(categoryId);
    }

    public ResponseEntity<?> addProduct(Product product, Long categoryId) {

        Optional<Category> category = categoryRepo.findById(categoryId);
        Optional<Product> product1 = productRepo.findByName(product.getName());

        if(category.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(product1.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Category categoryName = category.get();
        product.setCategory(categoryName);
        productRepo.save(product);

        return ResponseEntity.status(HttpStatus.OK).body("product added successfully");
    }


}
