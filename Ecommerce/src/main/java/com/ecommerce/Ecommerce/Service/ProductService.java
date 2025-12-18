package com.ecommerce.Ecommerce.Service;

import com.ecommerce.Ecommerce.Exception.ResourceNotFoundException;
import com.ecommerce.Ecommerce.Models.Category;
import com.ecommerce.Ecommerce.Models.Product;
import com.ecommerce.Ecommerce.Payload.ProductDTO;
import com.ecommerce.Ecommerce.Payload.ProductResponse;
import com.ecommerce.Ecommerce.Repository.CategoryRepo;
import com.ecommerce.Ecommerce.Repository.ProductRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ecommerce.Ecommerce.Service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo, ModelMapper modelMapper, FileService fileService)
    {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }


    public ProductResponse getAllProducts(HttpServletRequest request) {

        System.out.println("username is : " + request.getAttribute("username"));

        List<Product> products =  productRepo.findAll();

        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);

        return productResponse;

    }

    /**
     * when you call findById(id) -- it returns an Optional<Product> object.
     * Optional is wrapper that may or may not contains a value.
     * get() is used to retrieve the value from the optional.
     */

    public Product getProductById(Long id) {
        return productRepo.findById(id).get();
    }

    public ProductResponse getByCategoryId(Long categoryId) {

        Category category = categoryRepo.findById(categoryId)
                            .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Product> products = productRepo.findByCategoryOrderByPriceAsc(category);

        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();

        productResponse.setContent(productDTOS);

        return productResponse;
    }

    public ProductDTO addProduct(ProductDTO productDTO, Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                            .orElseThrow(() -> new ResourceNotFoundException("Category not found", "CategoryId", categoryId));

        Product product = modelMapper.map(productDTO, Product.class);

        Optional<Product> product1 = productRepo.findByName(product.getName());

        if(product1.isPresent()){
            throw new RuntimeException("Product already exists!");
        }
        product.setCategory(category);
        product.setImageUrl("default.png");
        double specialPrice = product.getPrice() - ((product.getDiscount()*0.01)*product.getPrice());
        product.setSpecialPrice(specialPrice);

        Product savedProduct = productRepo.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }


    public ProductResponse searchProduct(String query) {

        List<Product> existProducts = productRepo.findByNameContainingIgnoreCase(query);

        if(query == null || query.isBlank()){
            List<Product> products = productRepo.findAll();

            List<ProductDTO> productDTOS = products.stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .toList();

            ProductResponse productResponse = new ProductResponse();
            productResponse.setContent(productDTOS);

            return productResponse;
        }

        List<ProductDTO> productDTOS = existProducts.stream()
                .map(product ->  modelMapper.map(product, ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();

        productResponse.setContent(productDTOS);

        return productResponse;

    }

    public List<Product> getAllProductInCategory() {
        return productRepo.findAll();
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {

        Product productFromDb = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        Product product = modelMapper.map(productDTO, Product.class);

        productFromDb.setName(product.getName());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setBrand(product.getBrand());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setStockQuantity(product.getStockQuantity());
        productFromDb.setDiscount(product.getDiscount());
        productFromDb.setSpecialPrice(product.getSpecialPrice());
        productFromDb.setProductAvailable(product.isProductAvailable());

        Product savedProduct =  productRepo.save(productFromDb);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    public ProductDTO deleteProductById(Long id) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

        productRepo.delete(product);

        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO updateProductImage(Long id, MultipartFile image) throws IOException {

        Product productFromDB = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        String fileName = fileService.uploadImage(path, image);

        productFromDB.setImageUrl(fileName);

        Product updatedProduct = productRepo.save(productFromDB);

        return modelMapper.map(updatedProduct, ProductDTO.class);
    }
}
