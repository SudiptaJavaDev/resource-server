package com.cloud.ecommerce.resourceserver.service;

import com.cloud.ecommerce.resourceserver.dto.ProductResponseDto;
import com.cloud.ecommerce.resourceserver.dto.ProductResponseListDto;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecParams;

public interface IProductService {

	
	public ProductResponseDto getProductById(long id);
	public ProductResponseListDto getProductList(ProductSpecParams specParams);
	
}
