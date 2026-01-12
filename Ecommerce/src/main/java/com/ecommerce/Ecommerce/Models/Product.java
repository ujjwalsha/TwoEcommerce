package com.ecommerce.Ecommerce.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.core.util.Json;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data //this is part of lombok library it will all constructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, message = "Product Name must contain at least 5 characters")
    private String name;

    @NotBlank
    @Size(min = 5, message = "description must be contains at least 5 characters")
    private String description;

    @NotBlank
    @Size(min = 2, message = "brand name must be contains at least 5 characters")
    private String brand;

    private double price;
    private double discount;
    private boolean productAvailable;
    private Integer stockQuantity;
    private String imageUrl;
    private double specialPrice;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy, HH:MM:SS")
    @JsonIgnore
    private Date createdAt;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy, HH:MM:SS")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;
}
