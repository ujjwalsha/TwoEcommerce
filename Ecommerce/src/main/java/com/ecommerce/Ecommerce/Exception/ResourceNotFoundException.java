package com.ecommerce.Ecommerce.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String field;
    private Long fieldId;

    public ResourceNotFoundException(String message, String field, Long fieldId)
    {
        super(String.format("%s not found with %s : %d", message, field, fieldId));
        this.resourceName = message;
        this.field = field;
        this.fieldId = fieldId;

    }

}
