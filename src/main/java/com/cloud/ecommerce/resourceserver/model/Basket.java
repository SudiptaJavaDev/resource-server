package com.cloud.ecommerce.resourceserver.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash("BASKET")
@Data
public class Basket {
	
	private String id;
	
	private List<BasketItem> items;
	
	public Basket(String basketId) {
		this.id = basketId;
		items = new ArrayList<>();
	}
	public Basket() {}
}
