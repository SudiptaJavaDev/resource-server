package com.cloud.ecommerce.resourceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Brand {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="brandId")
	private Long brandId;
	
	
	@Column(name="brandName")
	private String brandName;
	public Brand() {}
	
	public Brand(String brandName) {
		super();
		this.brandName= brandName;
	}
	
	public Brand(Long brandId) {
		super();
		this.brandId= brandId;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="brand")
	@JsonIgnore
	private Set<Product> products;
	
	
	
	
}
