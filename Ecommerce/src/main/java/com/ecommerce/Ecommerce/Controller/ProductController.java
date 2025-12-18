package com.ecommerce.Ecommerce.Controller;

import com.ecommerce.Ecommerce.Models.Product;
import com.ecommerce.Ecommerce.Payload.ProductDTO;
import com.ecommerce.Ecommerce.Payload.ProductResponse;
import com.ecommerce.Ecommerce.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    //@RequestBody -- used to bind the HTTP request body to java Object where you receive JSON OR XML data from a client
    @PostMapping("admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long categoryId)
    {
        ProductDTO savedProductDTO = productService.addProduct(productDTO, categoryId);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getProducts(HttpServletRequest request){

        logger.info("Hello endpoint called");
        ProductResponse productResponse = productService.getAllProducts(request);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){

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

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductByCategoryId(@PathVariable Long categoryId)
    {
        ProductResponse productResponse = productService.getByCategoryId(categoryId);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/category/0")
    public List<Product> getAllProductInCategory()
    {
        return productService.getAllProductInCategory();
    }

    @GetMapping("/public/products/keyword")
    public ResponseEntity<ProductResponse> searchProduct(@RequestParam(name = "query", required = false) String query)
    {
        ProductResponse productResponse =  productService.searchProduct(query);

        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/{id}")
    public ResponseEntity<ProductDTO>  updateProductById(@RequestBody ProductDTO productDTO, @PathVariable Long id)
    {
        ProductDTO updatedProductDTO = productService.updateProduct(productDTO, id);

        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long id)
    {
        ProductDTO deletedProduct = productService.deleteProductById(id);

        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    @PutMapping("/products/{id}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long id,
                                                         @RequestParam(name = "image")MultipartFile image) throws IOException {
          ProductDTO updateProduct =   productService.updateProductImage(id, image);

          return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
}
