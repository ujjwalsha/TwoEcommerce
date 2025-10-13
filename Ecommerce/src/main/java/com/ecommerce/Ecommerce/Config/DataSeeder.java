package com.ecommerce.Ecommerce.Config;

import com.ecommerce.Ecommerce.Models.Category;
import com.ecommerce.Ecommerce.Models.Product;
import com.ecommerce.Ecommerce.Repository.CategoryRepo;
import com.ecommerce.Ecommerce.Repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Component //spring manage itself
public class DataSeeder implements CommandLineRunner {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public DataSeeder(ProductRepo productRepo, CategoryRepo categoryRepo)
    {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }


    //(String... args) -- it means variable length argument, you can call the method any number of string argument, including None.
    @Override
    public void run(String... args) throws Exception {
        productRepo.deleteAll();
        categoryRepo.deleteAll();

        Category electronics = new Category();
        electronics.setName("Electronics");
        
        Category clothing = new Category();
        clothing.setName("Clothing");

        Category home = new Category();
        home.setName("Home and Kitchen");

        categoryRepo.saveAll(Arrays.asList(electronics, clothing, home));

        Product Phone = new Product();
        Phone.setName("iPhone 15 Pro Max");
        Phone.setDescription("6.7-inch Super Retina XDR display, A17 Pro chip, 48MP Pro camera system, titanium design, and all-day battery life");
        Phone.setBrand("iphone");
        Phone.setPrice(BigDecimal.valueOf(1900.0));
        Phone.setReleaseDate(new Date());
        Phone.setImageUrl("https://placehold.co/600x400 ");
        Phone.setProductAvailable(true);
        Phone.setStockQuantity(120);
        Phone.setCategory(electronics);

        Product laptop = new Product();
        laptop.setName("MacBook Air M2");
        laptop.setDescription("13.6″ Liquid Retina display, Apple M2 chip, 8GB RAM, 256GB SSD, lightweight design with up to 18 hours battery life");
        laptop.setBrand("apple");
        laptop.setPrice(BigDecimal.valueOf(9999.0));
        laptop.setReleaseDate(new Date());
        laptop.setImageUrl("https://placehold.co/600x400");
        laptop.setProductAvailable(true);
        laptop.setStockQuantity(120);
        laptop.setCategory(electronics);

        Product jacket = new Product();
        jacket.setName("Leather Biker Jacket");
        jacket.setDescription("Premium genuine leather biker jacket with a slim fit, durable zippers, and quilted lining for comfort. Perfect for casual wear and outdoor adventures.");
        jacket.setBrand("ONN");
        jacket.setPrice(BigDecimal.valueOf(1999.0));
        jacket.setReleaseDate(new Date());
        jacket.setImageUrl("https://placehold.co/600x400");
        jacket.setProductAvailable(false);
        jacket.setStockQuantity(120);
        jacket.setCategory(clothing);

        Product Blender = new Product();
        Blender.setName("NutriBlend High-Speed Blender");
        Blender.setDescription("Powerful 1000W blender with stainless steel blades, 3 speed settings, and pulse function. Perfect for smoothies, shakes, soups, and nut butters.");
        Blender.setBrand("iphone");
        Blender.setPrice(BigDecimal.valueOf(1999.0));
        Blender.setReleaseDate(new Date());
        Blender.setImageUrl("https://placehold.co/600x400");
        Blender.setProductAvailable(true);
        Blender.setStockQuantity(120);
        Blender.setCategory(home);

        productRepo.saveAll(Arrays.asList(Phone,laptop,jacket,Blender));
    }
}
