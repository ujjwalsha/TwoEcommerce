package com.ecommerce.Ecommerce.Config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {


    @Bean
    public Cloudinary cloudinary(){

        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dqlvby78r");
        config.put("api_key", "797531871373669");
        config.put("api_secrets", "f3H6wEGmjGuzGk2F2tor4YiGlRw");

        return new Cloudinary(config);
    }
}
