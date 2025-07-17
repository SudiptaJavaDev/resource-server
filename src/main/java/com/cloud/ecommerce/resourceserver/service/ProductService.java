package com.cloud.ecommerce.resourceserver.service;

import com.cloud.ecommerce.resourceserver.dto.ProductResponseDto;
import com.cloud.ecommerce.resourceserver.dto.ProductResponseListDto;
import com.cloud.ecommerce.resourceserver.exception.ResourceNotFoundException;
import com.cloud.ecommerce.resourceserver.model.Product;
import com.cloud.ecommerce.resourceserver.repository.ProductRepository;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecParams;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecificationTitleBrandCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {


	@Value("${pagination.page.size.default}")
	private Integer defaultPageSize;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductSpecificationTitleBrandCategory productSpecification;

	@Override
	public ProductResponseDto getProductById(long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		Product product = productOpt.orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
		ProductResponseDto prdto = new ProductResponseDto();
		prdto.populateDto(product);
		return prdto;
	}

	/**
	 * Retrieves a paginated list of products based on the provided search criteria.
	 * <p>
	 * Pagination Rules:
	 * 1. Page Index (1-based):
	 * - If <= 0: Returns all results without pagination
	 * - If > 0: Returns the specified page of results
	 * - If beyond total pages: Returns empty result set with pagination metadata
	 * <p>
	 * 2. Page Size:
	 * - If <= 0: Uses defaultPageSize from application properties
	 * - If > MaxPageSize (20): Uses MaxPageSize
	 * - Otherwise: Uses the specified page size
	 * <p>
	 * Response includes:
	 * - dataList: List of products (empty if none found)
	 * - totalCount: Total number of matching products
	 * - totalPages: Total number of pages available
	 * - pageIndex: Current page number (1-based)
	 * - pageSize: Number of items per page
	 *
	 * @param specParams Search criteria and pagination parameters
	 * @return ProductResponseListDto containing paginated results and metadata
	 */
	@Override
	public ProductResponseListDto getProductList(ProductSpecParams specParams) {
		// Initialize response DTO with empty data list
		ProductResponseListDto response = new ProductResponseListDto();
		response.setDataList(new ArrayList<>());

		// 1. Validate and set page size
		if (specParams.getPageSize() <= 0) {
			// Use default page size if invalid or not provided
			specParams.setPageSize(defaultPageSize);
		}

		// 2. Check if we should return all results (non-paginated)
		if (specParams.getPageIndex() <= 0) {
			return getAllProductsWithoutPagination(specParams, response);
		}

		// 3. Handle paginated results
		return getPaginatedProducts(specParams, response);
	}

	/**
	 * Fetches all matching products without pagination
	 */
	private ProductResponseListDto getAllProductsWithoutPagination(ProductSpecParams specParams,
																   ProductResponseListDto response) {
		List<Product> productList = productRepository.findAll(
				productSpecification.getProducts(specParams));

		if (productList != null) {
			response.setTotalCount((long) productList.size());
			response.setPageIndex(1);
			response.setPageSize(productList.size() > 0 ? productList.size() : 1);
			response.setTotalPages(1);

			for (Product product : productList) {
				response.getDataList().add(createProductDto(product));
			}
		}
		return response;
	}

	/**
	 * Fetches a specific page of products with pagination
	 */
	private ProductResponseListDto getPaginatedProducts(ProductSpecParams specParams,
														ProductResponseListDto response) {
		// Convert to 0-based index for Spring Data
		int pageIndex = Math.max(1, specParams.getPageIndex());
		Pageable paging = PageRequest.of(pageIndex - 1, specParams.getPageSize());

		// Execute paginated query
		Page<Product> pages = productRepository.findAll(
				productSpecification.getProducts(specParams), paging);

		// Set pagination metadata
		response.setTotalCount(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		response.setPageIndex(pages.getNumber() + 1); // Convert back to 1-based
		response.setPageSize(pages.getSize());

		// Map products to DTOs
		List<Product> productList = pages.getContent();
		if (productList != null) {
			for (Product product : productList) {
				response.getDataList().add(createProductDto(product));
			}
		}

		return response;
	}

	/**
	 * Creates a ProductResponseDto from a Product entity
	 */
	private ProductResponseDto createProductDto(Product product) {
		ProductResponseDto dto = new ProductResponseDto();
		dto.populateDto(product);
		return dto;
	}
}