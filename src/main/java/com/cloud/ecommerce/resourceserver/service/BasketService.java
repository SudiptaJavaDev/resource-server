package com.cloud.ecommerce.resourceserver.service;

import com.cloud.ecommerce.resourceserver.exception.BasketNotFoundException;
import com.cloud.ecommerce.resourceserver.model.Basket;
import com.cloud.ecommerce.resourceserver.model.BasketData;
import com.cloud.ecommerce.resourceserver.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService implements IBasketService {

    private final BasketRepository basketRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public BasketService(BasketRepository basketRepository, RedisTemplate<String, Object> redisTemplate) {
        this.basketRepository = basketRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Basket createBasket(BasketData basketData) {
        Basket basket = new Basket(basketData.getId());
        basket.setItems(basketData.getItems());
        if (basketRepository.findById(basket.getId()).isPresent()) {
            throw new IllegalArgumentException("Basket with id " + basket.getId() + " already exists");
        }
        return basketRepository.save(basket);
    }

    @Override
    public Basket getBasket(String id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new BasketNotFoundException(id));
    }

    @Override
    public void deleteBasket(String id) {
        basketRepository.deleteById(id);
    }

    @Override
    public Iterable<Basket> getAllBaskets() {
        List<Basket> baskets = new ArrayList<>();
        String pattern = "BASKET:*";
        
        try (Cursor<byte[]> cursor = redisTemplate.getConnectionFactory()
                .getConnection()
                .keyCommands()
                .scan(ScanOptions.scanOptions()
                        .match(pattern)
                        .count(1000)
                        .build())) {
            
            while (cursor.hasNext()) {
                byte[] key = cursor.next();
                String basketKey = new String(key);
                // Extract the ID from the key (remove the 'BASKET:' prefix)
                String id = basketKey.substring(basketKey.indexOf(':') + 1);
                Basket basket = basketRepository.findById(id).orElse(null);
                if (basket != null) {
                    baskets.add(basket);
                }
            }
        }
        
        return baskets;
    }
}
