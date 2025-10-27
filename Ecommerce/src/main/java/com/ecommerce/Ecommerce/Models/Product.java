package com.ecommerce.Ecommerce.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;
import java.net.ProtocolFamily;
import java.util.Date;

@Entity
@Data //this is part of lombok library it will all constructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;
    private String imageUrl;

    public Product()
    {
        this.releaseDate = new Date();
    }

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
