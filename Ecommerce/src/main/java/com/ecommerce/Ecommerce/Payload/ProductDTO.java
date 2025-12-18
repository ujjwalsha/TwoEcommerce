package com.ecommerce.Ecommerce.Payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String brand;
    private String description;
    private String imageUrl;
    private Integer stockQuantity;
    private boolean productAvailable;
    private double price;
    private double discount;
    private double specialPrice;
}
