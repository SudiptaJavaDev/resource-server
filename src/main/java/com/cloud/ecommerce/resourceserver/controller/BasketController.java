package com.cloud.ecommerce.resourceserver.controller;

import com.cloud.ecommerce.resourceserver.model.Basket;
import com.cloud.ecommerce.resourceserver.model.BasketData;
import com.cloud.ecommerce.resourceserver.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 
 */
@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping()
    public ResponseEntity<Basket> createBasket(@RequestBody BasketData basketData) {
        Basket basket = basketService.createBasket(basketData);
        return ResponseEntity.ok(basket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasket(@PathVariable String id) {
        Basket basket = basketService.getBasket(id);
        return ResponseEntity.ok(basket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBasket(@PathVariable String id) {
        basketService.deleteBasket(id);
        return ResponseEntity.ok(id+" deleted");
    }

    @GetMapping("/allBaskets")
    public ResponseEntity<Iterable<Basket>> getBaskets() {
        return ResponseEntity.ok(basketService.getAllBaskets());
    }
}
