package com.ecommerce.Ecommerce.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.core.util.Json;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data //this is part of lombok library it will all constructor
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
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
    /**
     * JsonIgnore is an annotation from jackson(a popular json library in java).
     * it is used to tell jackson not to include a particular field or method when serializing or deserializing JSON
     * Security reason -- Hide sensitive data like password, tokens, API keys.
     * Avoid recursion.
     * Clean response -- Remove unnecessary fields from API response.
     * JSON serialization means converting  a java object into JSON. using ObjectMapper
     * JSON deserialization means converting JSON back to a java Object.using ObjectMapper
     */
    private Category category;
}
