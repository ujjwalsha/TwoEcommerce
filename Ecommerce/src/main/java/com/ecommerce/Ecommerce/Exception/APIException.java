package com.ecommerce.Ecommerce.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }
}
