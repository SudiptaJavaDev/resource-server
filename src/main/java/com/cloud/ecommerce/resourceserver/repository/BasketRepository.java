package com.cloud.ecommerce.resourceserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cloud.ecommerce.resourceserver.model.Basket;

@Repository
public interface BasketRepository extends  CrudRepository<Basket, String>{
   
}
