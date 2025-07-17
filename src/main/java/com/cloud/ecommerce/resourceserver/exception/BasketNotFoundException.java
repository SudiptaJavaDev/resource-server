package com.cloud.ecommerce.resourceserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BasketNotFoundException extends RuntimeException {
    public BasketNotFoundException(String id) {
        super("Basket not found with id: " + id);
    }

    public BasketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
