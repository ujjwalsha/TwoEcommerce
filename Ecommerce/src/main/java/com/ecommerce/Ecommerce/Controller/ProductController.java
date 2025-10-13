package com.ecommerce.Ecommerce.Controller;

import com.ecommerce.Ecommerce.Models.Product;
import com.ecommerce.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
 //frontend is on another port and backend is on another port to communicate we have to make bridge
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){

        Product product = productService.getProductById(id);

        if(product != null)
        {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductByCategoryId(@PathVariable Long categoryId)
    {
        return productService.getByCategoryId(categoryId);
    }

    //@RequestBody -- used to bind the HTTP request body to java Object where you receive JSON OR XML data from a client
    @PostMapping("/product/add/{categoryId}")
    public ResponseEntity<?> addProduct(@RequestBody Product product, @PathVariable Long categoryId)
    {
        return productService.addProduct(product, categoryId);
    }






}
