package com.cloud.ecommerce.resourceserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BasketData {
    @Nonnull
    private String id;  // Changed to lowercase
    
    @JsonProperty("items")
    private List<BasketItem> items;  // Changed to lowercase and added JsonProperty
    
    public BasketData() {}
}
