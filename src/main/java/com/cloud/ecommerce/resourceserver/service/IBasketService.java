package com.cloud.ecommerce.resourceserver.service;

import com.cloud.ecommerce.resourceserver.model.Basket;
import com.cloud.ecommerce.resourceserver.model.BasketData;

import java.util.Optional;

public interface IBasketService {
    Basket createBasket(BasketData basketData);
    Basket getBasket(String id);
    void deleteBasket(String id);
    Iterable<Basket> getAllBaskets();
}



