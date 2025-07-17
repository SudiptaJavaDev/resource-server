package com.cloud.ecommerce.resourceserver.controller;

import com.cloud.ecommerce.resourceserver.dto.ProductResponseDto;
import com.cloud.ecommerce.resourceserver.dto.ProductResponseListDto;
import com.cloud.ecommerce.resourceserver.model.Brand;
import com.cloud.ecommerce.resourceserver.model.Category;
import com.cloud.ecommerce.resourceserver.repository.BrandRepository;
import com.cloud.ecommerce.resourceserver.repository.CategoryRepository;
import com.cloud.ecommerce.resourceserver.service.IProductService;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ProductController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private IProductService productService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories != null) {
            return ResponseEntity.ok(categories);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = brandRepository.findAll();
        if(brands != null) {
            return ResponseEntity.ok(brands);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products")
    public ResponseEntity<ProductResponseListDto> getProducts(ProductSpecParams requestParams) {

        ProductResponseListDto productList = productService.getProductList(requestParams);
        return ResponseEntity.ok(productList);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id) {

        ProductResponseDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

}
